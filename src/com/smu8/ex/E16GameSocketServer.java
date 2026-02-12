package com.smu8.ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class E16GameSocketServer {

    public static final int WORLD_WIDTH = 900;
    public static final int WORLD_HEIGHT = 620;
    public static final int PLAYER_RADIUS = 14;
    public static final int MISSILE_RADIUS = 5;
    public static final int STEP = 14;
    public static final int MISSILE_SPEED = 18;

    private static final String USERS_PREFIX = "@USERS ";
    private static final String STATE_PREFIX = "@STATE ";
    private static final String MISSILES_PREFIX = "@MISSILES ";
    private static final String MOVE_PREFIX = "MOVE ";
    private static final String FIRE_CMD = "FIRE";
    private static final long GAME_TICK_MS = 33L;
    private static final long MISSILE_COOLDOWN_MS = 220L;

    private final List<PlayerClient> clients = Collections.synchronizedList(new ArrayList<>());
    private final List<Missile> missiles = new ArrayList<>();
    private final Random random = new Random();

    public static void main(String[] args) throws Exception {
        new E16GameSocketServer().start(7777);
    }

    public void start(int port) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[GAME-SERVER] listening on " + port);

            Thread gameLoopThread = new Thread(this::gameLoop, "game-loop");
            gameLoopThread.setDaemon(true);
            gameLoopThread.start();

            while (true) {
                Socket socket = serverSocket.accept();
                PlayerClient client = new PlayerClient(socket);
                clients.add(client);

                Thread worker = new Thread(() -> handle(client));
                worker.setDaemon(true);
                worker.start();
            }
        }
    }

    private void handle(PlayerClient client) {
        try (client) {
            String requestedNickname = client.in.readLine();
            client.nickname = makeUniqueNickname(client, sanitizeNickname(requestedNickname));
            client.x = randomRange(PLAYER_RADIUS, WORLD_WIDTH - PLAYER_RADIUS);
            client.y = randomRange(PLAYER_RADIUS, WORLD_HEIGHT - PLAYER_RADIUS);
            client.colorHex = makeUniqueColorHex(client);
            client.lastDirection = "UP";
            client.kills = 0;
            client.deaths = 0;

            broadcast("[SYSTEM] " + client.nickname + " joined.");
            broadcastUsers();
            broadcastState();
            broadcastMissiles();

            String line;
            while ((line = client.in.readLine()) != null) {
                String cmd = line.trim();
                if (cmd.isBlank()) continue;
                if (cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("/quit")) break;

                if (cmd.regionMatches(true, 0, MOVE_PREFIX, 0, MOVE_PREFIX.length())) {
                    move(client, cmd.substring(MOVE_PREFIX.length()).trim());
                    continue;
                }

                if (cmd.equalsIgnoreCase(FIRE_CMD)) {
                    fire(client, "");
                    continue;
                }

                if (cmd.regionMatches(true, 0, FIRE_CMD + " ", 0, FIRE_CMD.length() + 1)) {
                    fire(client, cmd.substring(FIRE_CMD.length()).trim());
                }
            }
        } catch (IOException e) {
            System.out.println("[GAME-SERVER] disconnect: " + e.getMessage());
        } finally {
            synchronized (clients) {
                clients.remove(client);
                missiles.removeIf(m -> m.owner == client);
            }
            if (client.nickname != null) {
                broadcast("[SYSTEM] " + client.nickname + " left.");
            }
            broadcastUsers();
            broadcastState();
            broadcastMissiles();
        }
    }

    private void move(PlayerClient client, String directionRaw) {
        String direction = normalizeDirection(directionRaw);
        if (direction == null) return;

        synchronized (clients) {
            int nextX = client.x;
            int nextY = client.y;
            client.lastDirection = direction;

            switch (direction) {
                case "UP" -> nextY -= STEP;
                case "DOWN" -> nextY += STEP;
                case "LEFT" -> nextX -= STEP;
                case "RIGHT" -> nextX += STEP;
                default -> {
                    return;
                }
            }

            client.x = clamp(nextX, PLAYER_RADIUS, WORLD_WIDTH - PLAYER_RADIUS);
            client.y = clamp(nextY, PLAYER_RADIUS, WORLD_HEIGHT - PLAYER_RADIUS);
        }
        broadcastState();
    }

    private void fire(PlayerClient client, String directionRaw) {
        String direction = normalizeDirection(directionRaw);
        if (direction == null) direction = client.lastDirection;
        if (direction == null) direction = "UP";

        int dx = 0;
        int dy = 0;
        switch (direction) {
            case "UP" -> dy = -1;
            case "DOWN" -> dy = 1;
            case "LEFT" -> dx = -1;
            case "RIGHT" -> dx = 1;
            default -> {
                return;
            }
        }

        long now = System.currentTimeMillis();
        synchronized (clients) {
            if (now - client.lastFireAt < MISSILE_COOLDOWN_MS) return;
            client.lastFireAt = now;

            int startX = client.x + dx * (PLAYER_RADIUS + MISSILE_RADIUS + 4);
            int startY = client.y + dy * (PLAYER_RADIUS + MISSILE_RADIUS + 4);
            missiles.add(new Missile(
                    client,
                    clamp(startX, MISSILE_RADIUS, WORLD_WIDTH - MISSILE_RADIUS),
                    clamp(startY, MISSILE_RADIUS, WORLD_HEIGHT - MISSILE_RADIUS),
                    dx,
                    dy,
                    client.colorHex
            ));
        }
        broadcastMissiles();
    }

    private void gameLoop() {
        while (true) {
            try {
                Thread.sleep(GAME_TICK_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            tickMissiles();
        }
    }

    private void tickMissiles() {
        List<String> logs = new ArrayList<>();
        boolean missilesChanged;
        boolean stateChanged = false;

        synchronized (clients) {
            if (missiles.isEmpty()) return;
            missilesChanged = true;

            Iterator<Missile> it = missiles.iterator();
            while (it.hasNext()) {
                Missile missile = it.next();
                missile.x += missile.dx * MISSILE_SPEED;
                missile.y += missile.dy * MISSILE_SPEED;

                if (missile.x < MISSILE_RADIUS || missile.x > WORLD_WIDTH - MISSILE_RADIUS
                        || missile.y < MISSILE_RADIUS || missile.y > WORLD_HEIGHT - MISSILE_RADIUS) {
                    it.remove();
                    continue;
                }

                PlayerClient victim = findHitPlayer(missile);
                if (victim != null) {
                    it.remove();
                    victim.deaths++;
                    missile.owner.kills++;
                    respawn(victim);
                    stateChanged = true;
                    logs.add("[SYSTEM] " + missile.owner.nickname + " ▶ " + victim.nickname + " 처치!");
                }
            }
        }

        for (String log : logs) {
            broadcast(log);
        }
        if (stateChanged) {
            broadcastUsers();
            broadcastState();
        }
        if (missilesChanged) {
            broadcastMissiles();
        }
    }

    private PlayerClient findHitPlayer(Missile missile) {
        int hitDistance = PLAYER_RADIUS + MISSILE_RADIUS;
        int hitDistanceSq = hitDistance * hitDistance;
        for (PlayerClient target : clients) {
            if (target == missile.owner) continue;
            int dx = target.x - missile.x;
            int dy = target.y - missile.y;
            if ((dx * dx) + (dy * dy) <= hitDistanceSq) {
                return target;
            }
        }
        return null;
    }

    private void respawn(PlayerClient client) {
        client.x = randomRange(PLAYER_RADIUS, WORLD_WIDTH - PLAYER_RADIUS);
        client.y = randomRange(PLAYER_RADIUS, WORLD_HEIGHT - PLAYER_RADIUS);
    }

    private String normalizeDirection(String rawDirection) {
        if (rawDirection == null) return null;
        String direction = rawDirection.trim().toUpperCase(Locale.ROOT);
        return switch (direction) {
            case "UP", "W" -> "UP";
            case "DOWN", "S" -> "DOWN";
            case "LEFT", "A" -> "LEFT";
            case "RIGHT", "D" -> "RIGHT";
            default -> null;
        };
    }

    private void broadcastUsers() {
        String line;
        synchronized (clients) {
            line = USERS_PREFIX + clients.stream()
                    .sorted(Comparator.comparing(c -> c.nickname))
                    .map(c -> c.nickname + " [K:" + c.kills + " D:" + c.deaths + "]")
                    .collect(Collectors.joining(","));
        }
        broadcast(line);
    }

    private void broadcastMissiles() {
        String line;
        synchronized (clients) {
            line = MISSILES_PREFIX + missiles.stream()
                    .map(m -> m.x + "," + m.y + "," + m.colorHex)
                    .sorted()
                    .collect(Collectors.joining("|"));
        }
        broadcast(line);
    }

    private void broadcastState() {
        String line;
        synchronized (clients) {
            line = STATE_PREFIX + clients.stream()
                    .sorted(Comparator.comparing(c -> c.nickname))
                    .map(c -> c.nickname + "," + c.x + "," + c.y + "," + c.colorHex + "," + c.kills + "," + c.deaths)
                    .collect(Collectors.joining("|"));
        }
        broadcast(line);
    }

    private void broadcast(String msg) {
        System.out.println(msg);
        synchronized (clients) {
            Iterator<PlayerClient> it = clients.iterator();
            while (it.hasNext()) {
                PlayerClient c = it.next();
                try {
                    c.out.write(msg);
                    c.out.newLine();
                    c.out.flush();
                } catch (IOException e) {
                    it.remove();
                    c.close();
                }
            }
        }
    }

    private String makeUniqueNickname(PlayerClient self, String base) {
        synchronized (clients) {
            Set<String> used = new HashSet<>();
            for (PlayerClient c : clients) {
                if (c != self) used.add(c.nickname);
            }

            if (!used.contains(base)) return base;

            int seq = 2;
            while (used.contains(base + "_" + seq)) seq++;
            return base + "_" + seq;
        }
    }

    private String sanitizeNickname(String nickname) {
        String clean = nickname == null ? "" : nickname.trim();
        if (clean.isBlank()) clean = "user";
        clean = clean.replace(",", "_").replace("|", "_");
        if (clean.length() > 20) clean = clean.substring(0, 20);
        return clean;
    }

    private int randomRange(int min, int max) {
        return min + random.nextInt((max - min) + 1);
    }

    private String randomColorHex() {
        int r = 80 + random.nextInt(156);
        int g = 80 + random.nextInt(156);
        int b = 80 + random.nextInt(156);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    private String makeUniqueColorHex(PlayerClient self) {
        synchronized (clients) {
            Set<String> used = new HashSet<>();
            for (PlayerClient c : clients) {
                if (c == self) continue;
                if (c.colorHex != null) used.add(c.colorHex);
            }
            for (int i = 0; i < 100; i++) {
                String candidate = randomColorHex();
                if (!used.contains(candidate)) return candidate;
            }
            return randomColorHex();
        }
    }

    private int clamp(int value, int min, int max) {
        if (value < min) return min;
        return Math.min(value, max);
    }

    private static final class PlayerClient implements AutoCloseable {
        final Socket socket;
        final BufferedReader in;
        final BufferedWriter out;

        volatile String nickname = "user";
        volatile int x = 0;
        volatile int y = 0;
        volatile String colorHex = "#3A86FF";
        volatile int kills = 0;
        volatile int deaths = 0;
        volatile String lastDirection = "UP";
        volatile long lastFireAt = 0L;

        PlayerClient(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        }

        @Override
        public void close() {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    private static final class Missile {
        final PlayerClient owner;
        int x;
        int y;
        final int dx;
        final int dy;
        final String colorHex;

        Missile(PlayerClient owner, int x, int y, int dx, int dy, String colorHex) {
            this.owner = owner;
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.colorHex = colorHex;
        }
    }
}

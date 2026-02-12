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

public class GameSocketServer {

    public static final int WORLD_WIDTH = 900;
    public static final int WORLD_HEIGHT = 620;
    public static final int PLAYER_RADIUS = 14;
    public static final int STEP = 14;

    private static final String USERS_PREFIX = "@USERS ";
    private static final String STATE_PREFIX = "@STATE ";
    private static final String MOVE_PREFIX = "MOVE ";

    private final List<PlayerClient> clients = Collections.synchronizedList(new ArrayList<>());
    private final Random random = new Random();

    public static void main(String[] args) throws Exception {
        new GameSocketServer().start(7777);
    }

    public void start(int port) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[GAME-SERVER] listening on " + port);
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
            client.colorHex = randomColorHex();

            broadcast("[SYSTEM] " + client.nickname + " joined.");
            broadcastUsers();
            broadcastState();

            String line;
            while ((line = client.in.readLine()) != null) {
                String cmd = line.trim();
                if (cmd.isBlank()) continue;
                if (cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("/quit")) break;

                if (cmd.regionMatches(true, 0, MOVE_PREFIX, 0, MOVE_PREFIX.length())) {
                    move(client, cmd.substring(MOVE_PREFIX.length()).trim());
                }
            }
        } catch (IOException e) {
            System.out.println("[GAME-SERVER] disconnect: " + e.getMessage());
        } finally {
            clients.remove(client);
            if (client.nickname != null) {
                broadcast("[SYSTEM] " + client.nickname + " left.");
            }
            broadcastUsers();
            broadcastState();
        }
    }

    private void move(PlayerClient client, String directionRaw) {
        String direction = directionRaw.toUpperCase(Locale.ROOT);
        int nextX = client.x;
        int nextY = client.y;

        switch (direction) {
            case "UP", "W" -> nextY -= STEP;
            case "DOWN", "S" -> nextY += STEP;
            case "LEFT", "A" -> nextX -= STEP;
            case "RIGHT", "D" -> nextX += STEP;
            default -> {
                return;
            }
        }

        client.x = clamp(nextX, PLAYER_RADIUS, WORLD_WIDTH - PLAYER_RADIUS);
        client.y = clamp(nextY, PLAYER_RADIUS, WORLD_HEIGHT - PLAYER_RADIUS);
        broadcastState();
    }

    private void broadcastUsers() {
        String line;
        synchronized (clients) {
            line = USERS_PREFIX + clients.stream()
                    .map(c -> c.nickname)
                    .sorted()
                    .collect(Collectors.joining(","));
        }
        broadcast(line);
    }

    private void broadcastState() {
        String line;
        synchronized (clients) {
            line = STATE_PREFIX + clients.stream()
                    .sorted(Comparator.comparing(c -> c.nickname))
                    .map(c -> c.nickname + "," + c.x + "," + c.y + "," + c.colorHex)
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
}

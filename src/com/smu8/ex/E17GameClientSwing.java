package com.smu8.ex;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class E17GameClientSwing extends JFrame {

    private static final String USERS_PREFIX = "@USERS ";
    private static final String STATE_PREFIX = "@STATE ";
    private static final String MISSILES_PREFIX = "@MISSILES ";

    private final GamePanel gamePanel = new GamePanel();
    private final DefaultListModel<String> userListModel = new DefaultListModel<>();
    private final JList<String> userList = new JList<>(userListModel);
    private final JTextArea logArea = new JTextArea();

    private final Map<String, PlayerView> players = new LinkedHashMap<>();
    private final List<MissileView> missiles = new ArrayList<>();

    private volatile boolean running = false;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String myNickname = "";
    private String facingDirection = "UP";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new E17GameClientSwing().setVisible(true));
    }

    public E17GameClientSwing() {
        setTitle("Socket Paint Game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1180, 760);
        setLocationRelativeTo(null);

        initUI();
        connectFlow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disconnect();
            }
        });
    }

    private void initUI() {
        gamePanel.setPreferredSize(new Dimension(E16GameSocketServer.WORLD_WIDTH, E16GameSocketServer.WORLD_HEIGHT));
        gamePanel.setBackground(new Color(243, 247, 252));
        gamePanel.setFocusable(true);

        userList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane userScroll = new JScrollPane(userList);
        userScroll.setBorder(BorderFactory.createTitledBorder("참가자"));

        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("로그"));

        JSplitPane sideSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, userScroll, logScroll);
        sideSplit.setResizeWeight(0.45);
        sideSplit.setDividerSize(6);
        sideSplit.setPreferredSize(new Dimension(260, 0));

        JSplitPane mainSplit = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(gamePanel),
                sideSplit
        );
        mainSplit.setResizeWeight(0.78);
        mainSplit.setDividerSize(7);

        JTextArea guide = new JTextArea("방향키/WASD 이동, SPACE 미사일 발사(현재 바라보는 방향)");
        guide.setEditable(false);
        guide.setBackground(new Color(246, 249, 255));
        guide.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        guide.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));

        setLayout(new BorderLayout());
        add(mainSplit, BorderLayout.CENTER);
        add(guide, BorderLayout.SOUTH);

        initKeyBindings();
    }

    private void initKeyBindings() {
        bindMove("UP", "UP");
        bindMove("DOWN", "DOWN");
        bindMove("LEFT", "LEFT");
        bindMove("RIGHT", "RIGHT");
        bindMove("W", "UP");
        bindMove("S", "DOWN");
        bindMove("A", "LEFT");
        bindMove("D", "RIGHT");
        bindFire("SPACE");
        bindFire("ENTER");
    }

    private void bindMove(String key, String direction) {
        String actionId = "move_" + key;
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), actionId);
        gamePanel.getActionMap().put(actionId, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                sendMove(direction);
            }
        });
    }

    private void bindFire(String key) {
        String actionId = "fire_" + key;
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), actionId);
        gamePanel.getActionMap().put(actionId, new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                sendFire();
            }
        });
    }

    private void connectFlow() {
        String host = JOptionPane.showInputDialog(this, "서버 주소", "127.0.0.1");
        if (host == null) {
            dispose();
            return;
        }

        String portStr = JOptionPane.showInputDialog(this, "포트", "7777");
        if (portStr == null) {
            dispose();
            return;
        }

        String nickname = JOptionPane.showInputDialog(this, "닉네임", "user");
        if (nickname == null) {
            dispose();
            return;
        }

        int port;
        try {
            port = Integer.parseInt(portStr.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "포트 숫자가 올바르지 않습니다.");
            dispose();
            return;
        }

        try {
            socket = new Socket(host.trim(), port);
            socket.setTcpNoDelay(true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            myNickname = nickname.trim().isBlank() ? "user" : nickname.trim();
            out.println(myNickname);

            running = true;
            appendLog("[SYSTEM] connected");
            gamePanel.requestFocusInWindow();

            Thread reader = new Thread(this::readLoop);
            reader.setDaemon(true);
            reader.start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "연결 실패: " + e.getMessage());
            dispose();
        }
    }

    private void readLoop() {
        try {
            String line;
            while (running && (line = in.readLine()) != null) {
                if (line.startsWith(USERS_PREFIX)) {
                    final String usersLine = line;
                    SwingUtilities.invokeLater(() -> updateUsers(usersLine));
                    continue;
                }

                if (line.startsWith(STATE_PREFIX)) {
                    final String stateLine = line;
                    SwingUtilities.invokeLater(() -> updateState(stateLine));
                    continue;
                }

                if (line.startsWith(MISSILES_PREFIX)) {
                    final String missileLine = line;
                    SwingUtilities.invokeLater(() -> updateMissiles(missileLine));
                    continue;
                }

                final String msg = line;
                SwingUtilities.invokeLater(() -> appendLog(msg));
            }
        } catch (IOException ignored) {
        } finally {
            SwingUtilities.invokeLater(() -> {
                appendLog("[SYSTEM] disconnected");
                userListModel.clear();
                players.clear();
                missiles.clear();
                gamePanel.repaint();
            });
            disconnect();
        }
    }

    private void updateUsers(String line) {
        String payload = line.substring(USERS_PREFIX.length()).trim();
        userListModel.clear();
        if (payload.isBlank()) return;
        String[] users = payload.split(",");
        for (String user : users) {
            String name = user.trim();
            if (!name.isBlank()) userListModel.addElement(name);
        }
    }

    private void updateState(String line) {
        String payload = line.substring(STATE_PREFIX.length()).trim();
        players.clear();
        if (!payload.isBlank()) {
            String[] chunks = payload.split("\\|");
            for (String chunk : chunks) {
                String[] data = chunk.split(",", 6);
                if (data.length < 6) continue;
                String nick = data[0].trim();
                if (nick.isBlank()) continue;

                try {
                    int x = Integer.parseInt(data[1].trim());
                    int y = Integer.parseInt(data[2].trim());
                    Color color = Color.decode(data[3].trim());
                    int kills = Integer.parseInt(data[4].trim());
                    int deaths = Integer.parseInt(data[5].trim());
                    players.put(nick, new PlayerView(nick, x, y, color, kills, deaths));
                } catch (Exception ignored) {
                }
            }
        }
        gamePanel.repaint();
    }

    private void updateMissiles(String line) {
        String payload = line.substring(MISSILES_PREFIX.length()).trim();
        missiles.clear();
        if (!payload.isBlank()) {
            String[] chunks = payload.split("\\|");
            for (String chunk : chunks) {
                String[] data = chunk.split(",", 3);
                if (data.length < 3) continue;
                try {
                    int x = Integer.parseInt(data[0].trim());
                    int y = Integer.parseInt(data[1].trim());
                    Color color = Color.decode(data[2].trim());
                    missiles.add(new MissileView(x, y, color));
                } catch (Exception ignored) {
                }
            }
        }
        gamePanel.repaint();
    }

    private void sendMove(String direction) {
        if (!running || out == null) return;
        String normalized = normalizeDirection(direction);
        if (normalized == null) return;
        facingDirection = normalized;
        out.println("MOVE " + normalized);
    }

    private void sendFire() {
        if (!running || out == null) return;
        out.println("FIRE " + facingDirection);
    }

    private String normalizeDirection(String rawDirection) {
        if (rawDirection == null) return null;
        String direction = rawDirection.trim().toUpperCase();
        return switch (direction) {
            case "UP", "W" -> "UP";
            case "DOWN", "S" -> "DOWN";
            case "LEFT", "A" -> "LEFT";
            case "RIGHT", "D" -> "RIGHT";
            default -> null;
        };
    }

    private void appendLog(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void disconnect() {
        running = false;
        try {
            if (socket != null) socket.close();
        } catch (Exception ignored) {
        }
        socket = null;
        in = null;
        out = null;
    }

    private final class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            drawGrid(g2);
            for (MissileView m : missiles) {
                drawMissile(g2, m);
            }
            for (PlayerView p : players.values()) {
                drawPlayer(g2, p);
            }
            g2.dispose();
        }

        private void drawGrid(Graphics2D g2) {
            g2.setColor(new Color(226, 234, 245));
            for (int x = 0; x < getWidth(); x += 32) {
                g2.drawLine(x, 0, x, getHeight());
            }
            for (int y = 0; y < getHeight(); y += 32) {
                g2.drawLine(0, y, getWidth(), y);
            }
        }

        private void drawMissile(Graphics2D g2, MissileView missile) {
            int r = E16GameSocketServer.MISSILE_RADIUS;
            int d = r * 2;
            g2.setColor(missile.color);
            g2.fillOval(missile.x - r, missile.y - r, d, d);
            g2.setColor(new Color(21, 30, 45));
            g2.setStroke(new BasicStroke(1.6f));
            g2.drawOval(missile.x - r, missile.y - r, d, d);
        }

        private void drawPlayer(Graphics2D g2, PlayerView player) {
            int r = E16GameSocketServer.PLAYER_RADIUS;
            int d = r * 2;

            g2.setColor(player.color);
            g2.fillOval(player.x - r, player.y - r, d, d);

            if (player.nickname.equals(myNickname)) {
                g2.setStroke(new BasicStroke(3f));
                g2.setColor(new Color(45, 57, 78));
                g2.drawOval(player.x - r - 2, player.y - r - 2, d + 4, d + 4);
            }

            g2.setColor(new Color(25, 33, 48));
            g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
            g2.drawString(player.nickname + " K:" + player.kills + " D:" + player.deaths, player.x - r, player.y - r - 6);
        }
    }

    private static final class PlayerView {
        final String nickname;
        final int x;
        final int y;
        final Color color;
        final int kills;
        final int deaths;

        PlayerView(String nickname, int x, int y, Color color, int kills, int deaths) {
            this.nickname = nickname;
            this.x = x;
            this.y = y;
            this.color = color;
            this.kills = kills;
            this.deaths = deaths;
        }
    }

    private static final class MissileView {
        final int x;
        final int y;
        final Color color;

        MissileView(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}

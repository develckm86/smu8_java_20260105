package com.smu8.ex;

import javax.swing.*;
import java.awt.*;

public class Q3BoatMove {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Q3 Boat Move");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BoatPanel panel = new BoatPanel();
            f.setContentPane(panel);

            f.setSize(800, 420);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

            panel.start(); // 애니메이션 시작
        });
    }

    static class BoatPanel extends JPanel {
        private int boatX = -140;        // 배 시작 위치
        private final int boatY = 240;   // 배 기준 y

        private final Timer timer;

        BoatPanel() {
            setBackground(Color.BLACK);

            // 30ms마다 이벤트 발생(EDT에서 실행)
            timer = new Timer(30, e -> {
                boatX += 2; // 오른쪽으로 이동

                // 화면 밖으로 나가면 다시 왼쪽에서 시작
                if (boatX > getWidth() + 140) {
                    boatX = -140;
                }

                repaint(); // 상태 변경 후 다시 그리기 요청
            });
        }

        void start() {
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 잔상 방지

            int w = getWidth();
            int h = getHeight();

            // 바다(아래 절반)
            g.setColor(new Color(10, 40, 80));
            g.fillRect(0, h / 2, w, h / 2);

            // 물결(간단한 호)
            g.setColor(new Color(30, 80, 140));
            for (int x = 0; x < w; x += 40) {
                g.drawArc(x, h / 2 + 40, 40, 20, 0, 180);
            }

            // 배 그리기(도형 조합)
            int x = boatX;
            int y = boatY;

            // 선체(둥근 사각형)
            g.setColor(new Color(120, 70, 30));
            g.fillRoundRect(x, y, 140, 30, 10, 10);

            // 선체 아래(삼각형)
            g.setColor(new Color(90, 50, 20));
            g.fillPolygon(
                    new int[]{x + 10, x + 130, x + 110},
                    new int[]{y + 30, y + 30, y + 55},
                    3
            );

            // 돛대
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x + 70, y - 80, 4, 80);

            // 돛(삼각형)
            g.setColor(new Color(220, 220, 220));
            g.fillPolygon(
                    new int[]{x + 72, x + 72, x + 125},
                    new int[]{y - 80, y - 20, y - 20},
                    3
            );

            // 선실
            g.setColor(new Color(170, 170, 170));
            g.fillRect(x + 25, y - 25, 35, 25);

            // 창문
            g.setColor(Color.YELLOW);
            g.fillRect(x + 30, y - 20, 8, 8);
            g.fillRect(x + 45, y - 20, 8, 8);
        }
    }
}

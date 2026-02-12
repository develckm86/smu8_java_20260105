package com.smu8.ex;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Q2SnowRandomStatic {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Q2 Random Snow (Static)");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            f.setContentPane(new SnowPanel());

            f.setSize(640, 420);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    static class SnowPanel extends JPanel {
        private final Random random = new Random();

        SnowPanel() {
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 배경 정리

            g.setColor(Color.WHITE);

            int w = getWidth();
            int h = getHeight();

            // 매번 paint될 때마다 새로 뿌리면 "정적"이 아니라 "재생성"이지만
            // 이 문제는 단순히 랜덤으로 그리는 것 자체가 목표라 여기서는 OK
            for (int i = 0; i < 200; i++) {
                int x = random.nextInt(Math.max(w, 1));
                int y = random.nextInt(Math.max(h, 1));
                int r = 1 + random.nextInt(3); // 1~3
                g.fillOval(x, y, r, r);
            }
        }
    }
}

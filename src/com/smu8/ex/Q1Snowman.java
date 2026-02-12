package com.smu8.ex;

import javax.swing.*;
import java.awt.*;

public class Q1Snowman {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Q1 Snowman");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 캔버스 패널 부착
            f.setContentPane(new SnowmanPanel());

            f.setSize(520, 360);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    static class SnowmanPanel extends JPanel {
        SnowmanPanel() {
            setBackground(Color.BLACK); // 검은 배경
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 배경 정리(잔상 방지)

            // 몸(원 3개)
            g.setColor(Color.WHITE);
            g.fillOval(210, 170, 120, 120); // 아래
            g.fillOval(225, 95, 90, 90);    // 중간
            g.fillOval(240, 35, 60, 60);    // 머리

            // 눈(작은 검은 원)
            g.setColor(Color.BLACK);
            g.fillOval(258, 55, 6, 6);
            g.fillOval(276, 55, 6, 6);

            // 코(주황색)
            g.setColor(Color.ORANGE);
            g.fillRect(270, 64, 16, 3);

            // 입(점)
            g.setColor(Color.BLACK);
            g.fillOval(260, 75, 4, 4);
            g.fillOval(266, 78, 4, 4);
            g.fillOval(272, 79, 4, 4);
            g.fillOval(278, 78, 4, 4);
            g.fillOval(284, 75, 4, 4);

            // 팔(갈색 선)
            g.setColor(new Color(140, 90, 40));
            g.drawLine(225, 135, 170, 110);
            g.drawLine(315, 135, 370, 110);
        }
    }
}

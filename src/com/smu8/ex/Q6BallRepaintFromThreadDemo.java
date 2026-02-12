package com.smu8.ex;

import javax.swing.*;
import java.awt.*;

public class Q6BallRepaintFromThreadDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Repaint From Thread Demo");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            BallPanel panel = new BallPanel();
            f.setContentPane(panel);

            f.setSize(520, 220);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

            // 임의의 스레드가 좌표 변경 + repaint를 수행
            panel.startMoveAndRepaintByThread();
        });
    }

    static class BallPanel extends JPanel {
        private int x = 30;         // 이동 상태(State)
        private final int y = 100;
        private final int r = 18;

        BallPanel() {
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 상태(x)를 읽어 화면에 렌더(Render)
            g.setColor(Color.WHITE);
            g.fillOval(x - r, y - r, r * 2, r * 2);
        }

        void startMoveAndRepaintByThread() {
            new Thread(() -> {
                while (true) {
                    // 1) 상태를 EDT가 아닌 스레드에서 변경
                    x += 1;

                    // 2) repaint 요청도 EDT가 아닌 스레드에서 호출
                    //    경우에 따라 순간적으로 깜빡이거나, 이벤트와 충돌 시 불안정해질 수 있다.
                    repaint();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }).start();
        }
    }
}

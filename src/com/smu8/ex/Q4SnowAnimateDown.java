package com.smu8.ex;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Q4SnowAnimateDown {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Q4 Snow Animate (Down)");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SnowPanel panel = new SnowPanel();
            f.setContentPane(panel);

            f.setSize(700, 450);
            f.setLocationRelativeTo(null);
            f.setVisible(true);

            panel.start();
        });
    }

    static class SnowPanel extends JPanel {
        private static final int COUNT = 200;

        private final Random random = new Random();

        // 눈송이 상태(State): 위치 + 크기 + 속도
        private final int[] xs = new int[COUNT];
        private final int[] ys = new int[COUNT];
        private final int[] rs = new int[COUNT];
        private final int[] vys = new int[COUNT]; // 아래로 떨어지는 속도(픽셀)

        private final Timer timer;

        SnowPanel() {
            setBackground(Color.BLACK);

            // 패널 크기가 아직 0일 수 있으므로, 시작할 때 한 번 세팅
            initSnow();

            // 30ms마다(부드럽게) 눈을 아래로 이동
            timer = new Timer(30, e -> {
                moveSnowDown(); // 상태 갱신(아래로 이동)
                repaint();      // 화면 갱신 요청(EDT에서 실행)
            });
        }

        void start() {
            timer.start();
        }

        // 초기 생성(눈송이를 "뿌려두고" 움직이게 함)
        private void initSnow() {
            int w = Math.max(getWidth(), 700); // 초기엔 getWidth()가 0일 수 있어 기본값 사용
            int h = Math.max(getHeight(), 450);

            for (int i = 0; i < COUNT; i++) {
                xs[i] = random.nextInt(w);     // 랜덤 x
                ys[i] = random.nextInt(h);     // 랜덤 y(화면 안에 분포)
                rs[i] = 1 + random.nextInt(3); // 반지름(1~3)
                vys[i] = 1 + random.nextInt(3); // 속도(1~3px)
            }
        }

        // 눈송이를 아래로 이동시키고, 화면 밖으로 나가면 다시 위로 올려 재사용
        private void moveSnowDown() {
            int w = Math.max(getWidth(), 1);
            int h = Math.max(getHeight(), 1);

            for (int i = 0; i < COUNT; i++) {
                ys[i] += vys[i]; // 아래로 이동

                // 화면 아래로 나가면 위쪽에서 다시 떨어지게 "재활용"
                if (ys[i] > h) {
                    ys[i] = -rs[i];          // 살짝 위에서 시작
                    xs[i] = random.nextInt(w); // x는 다시 랜덤
                    rs[i] = 1 + random.nextInt(3);
                    vys[i] = 1 + random.nextInt(3);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 배경 지우기 → 잔상 방지

            g.setColor(Color.WHITE);

            // 눈 그리기(현재 상태를 렌더)
            for (int i = 0; i < COUNT; i++) {
                g.fillOval(xs[i], ys[i], rs[i], rs[i]);
            }
        }
    }
}
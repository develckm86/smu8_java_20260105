package com.smu8.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Q5StarStamp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Q5 Star Stamp");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            StarPanel panel = new StarPanel();
            f.setContentPane(panel);

            f.setSize(720, 480);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    static class StarPanel extends JPanel {
        // 클릭된 별의 중심 좌표를 상태로 저장
        private final List<Point> stars = new ArrayList<>();

        StarPanel() {
            setBackground(Color.BLACK);

            // 마우스 클릭 이벤트 등록(EDT에서 이벤트 처리)
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // 클릭한 위치 저장(상태 누적)
                    stars.add(e.getPoint());

                    // 상태가 바뀌었으니 다시 그리기 요청
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 배경 정리

            g.setColor(Color.YELLOW);

            // 지금까지 저장된 모든 별을 다시 그린다(누적 표현)
            for (Point p : stars) {
                drawStar(g, p.x, p.y, 12);
            }

            // 안내 문구
            g.setColor(Color.WHITE);
            g.drawString("클릭할 때마다 별이 추가됩니다.", 10, 20);
        }

        // 별 그리기(간단한 선 조합)
        private void drawStar(Graphics g, int cx, int cy, int r) {
            // 중심에서 5방향으로 선을 그려 “별 느낌”만 구현
            g.drawLine(cx, cy - r, cx, cy + r);         // 세로
            g.drawLine(cx - r, cy, cx + r, cy);         // 가로
            g.drawLine(cx - r, cy - r, cx + r, cy + r); // 대각선1
            g.drawLine(cx - r, cy + r, cx + r, cy - r); // 대각선2
        }
    }
}


package com.smu8.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class E18DrawBasic {
    public static void main(String[] args) {
        // Swing UI 생성/표시는 EDT에서 실행하는 것이 원칙
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Draw With Image");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 캔버스 패널 부착
            f.setContentPane(new DrawPanel());

            f.setSize(1000, 650);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    static class DrawPanel extends JPanel {
        // 로딩된 이미지를 보관(없으면 null)
        private BufferedImage shipImg;

        DrawPanel() {
            setBackground(Color.BLACK);
            shipImg = loadImage("img.png");
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            int w = getWidth();
            int h = getHeight();

            // 제목
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Dialog", Font.BOLD, 20));
            g2.drawString("도형 + 이미지(drawImage) 데모", 20, 35);

            // (1) 기본 도형 몇 개
            g2.setColor(Color.CYAN);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(20, 60, 420, 60);
            g2.setStroke(new BasicStroke(1));

            g2.setColor(new Color(255, 150, 0));
            g2.fillRoundRect(20, 90, 220, 120, 30, 30);

            g2.setColor(new Color(120, 200, 255));
            g2.fillOval(270, 90, 170, 120);

            // (2) 배경(바다) 그리기
            int seaTop = 260;
            g2.setColor(new Color(10, 40, 80));
            g2.fillRect(0, seaTop, w, h - seaTop);

            // 물결
            g2.setColor(new Color(30, 80, 140));
            for (int x = 0; x < w; x += 50) {
                g2.drawArc(x, seaTop + 40, 50, 20, 0, 180);
            }

            // (3) 이미지 그리기: 원본 크기 그대로
            // drawImage(img, x, y, observer)
            if (shipImg != null) {
                int imgX = 60;
                int imgY = seaTop + 80;

                // 이미지 원본 크기 출력
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Dialog", Font.PLAIN, 14));
                g2.drawString("원본 이미지: " + shipImg.getWidth() + " x " + shipImg.getHeight(), imgX, imgY - 10);

                // 실제 이미지 출력
                g2.drawImage(shipImg, imgX, imgY, this);

                // (4) 이미지 크기 조절해서 그리기
                // drawImage(img, x, y, width, height, observer)
                int scaledW = 220;
                int scaledH = 140;
                int sx = 320;
                int sy = seaTop + 60;

                g2.setColor(Color.WHITE);
                g2.drawString("확대/축소 출력: " + scaledW + " x " + scaledH, sx, sy - 10);

                g2.drawImage(shipImg, sx, sy, scaledW, scaledH, this);

                // (5) Graphics2D 변환(회전) 후 이미지 그리기
                // 변환은 누적되므로 복구 필수
                AffineTransform old = g2.getTransform();

                // 회전 기준점(이미지 중심 근처)
                int cx = 720;
                int cy = seaTop + 130;

                g2.translate(cx, cy);               // 기준점을 (cx, cy)로 이동
                g2.rotate(Math.toRadians(-15));     // -15도 회전
                g2.translate(-scaledW / 2.0, -scaledH / 2.0); // 중심 정렬

                g2.setColor(Color.WHITE);
                g2.drawString("회전 출력(-15도)", 0, -10);

                g2.drawImage(shipImg, 0, 0, scaledW, scaledH, this);

                // 변환 복구
                g2.setTransform(old);
            } else {
                // 이미지 로딩 실패 시 안내 문구
                g2.setColor(Color.PINK);
                g2.setFont(new Font("Dialog", Font.PLAIN, 16));
                g2.drawString("이미지 로딩 실패: /images/ship.png 경로를 확인하세요.", 20, seaTop + 40);

                g2.setColor(Color.LIGHT_GRAY);
                g2.drawString("대신 도형 배를 그려서 표시합니다.", 20, seaTop + 65);

                // 대체 도형 배
                drawFallbackBoat(g2, 60, seaTop + 120);
            }
        }

        private BufferedImage loadImage(String path) {
            try {
                return ImageIO.read(new File(path));
            } catch (IOException e) {
                return null;
            }
        }

        // 이미지 로딩 실패 시, 도형으로 배를 그리는 간단한 대체 함수
        private void drawFallbackBoat(Graphics2D g2, int x, int y) {
            // 선체
            g2.setColor(new Color(120, 70, 30));
            g2.fillRoundRect(x, y, 180, 35, 12, 12);

            // 선체 아래
            g2.setColor(new Color(90, 50, 20));
            g2.fillPolygon(
                    new int[]{x + 15, x + 165, x + 140},
                    new int[]{y + 35, y + 35, y + 70},
                    3
            );

            // 돛대
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(x + 95, y - 90, 5, 90);

            // 돛
            g2.setColor(new Color(220, 220, 220));
            g2.fillPolygon(
                    new int[]{x + 98, x + 98, x + 160},
                    new int[]{y - 90, y - 20, y - 20},
                    3
            );
        }

        // 필요하면, 외부 파일이 없어도 보이도록 임시 이미지를 만들어 쓰는 방법(선택)
        @SuppressWarnings("unused")
        private BufferedImage createFallbackImage() {
            BufferedImage img = new BufferedImage(120, 80, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();

            // 투명 배경 위에 간단한 도형을 그려 “이미지처럼” 사용
            g2.setColor(new Color(120, 70, 30));
            g2.fillRoundRect(10, 35, 100, 20, 10, 10);

            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(60, 5, 4, 30);

            g2.setColor(new Color(220, 220, 220));
            g2.fillPolygon(new int[]{62, 62, 100}, new int[]{5, 30, 30}, 3);

            g2.dispose();
            return img;
        }
    }
}
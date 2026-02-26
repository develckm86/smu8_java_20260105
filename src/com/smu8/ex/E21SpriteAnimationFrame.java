package com.smu8.ex;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class E21SpriteAnimationFrame extends JFrame {

    public E21SpriteAnimationFrame() {
        setTitle("Sword Swing Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setContentPane(new SpritePanel());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(E21SpriteAnimationFrame::new);
    }
}

class SpritePanel extends JPanel {

    private BufferedImage spriteSheet;
    private int frameIndex = 0;
    private int frameWidth;
    private int frameHeight;
    private int totalFrames = 3;

    public SpritePanel() {
        try {
            spriteSheet = ImageIO.read(
                    new File("img.png")
            );

            frameWidth = spriteSheet.getWidth() / totalFrames;
            frameHeight = spriteSheet.getHeight();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(100, e -> {
            frameIndex = (frameIndex + 1) % totalFrames;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (spriteSheet == null) return;

        int sx = frameIndex * frameWidth;

        BufferedImage subImage = spriteSheet.getSubimage(
                sx,
                0,
                frameWidth,
                frameHeight
        );

        int centerX = (getWidth() - frameWidth) / 2;
        int centerY = (getHeight() - frameHeight) / 2;

        g.drawImage(subImage, centerX, centerY, null);
    }
}
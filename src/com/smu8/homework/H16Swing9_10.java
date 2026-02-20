package com.smu8.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class H16Swing9_10 extends JFrame {
    private JButton eBtn;
    private JButton wBtn;
    private JLabel label;
    public H16Swing9_10(){
        super("swing 9,10 문제");
        eBtn=new JButton("오른쪽");
        wBtn=new JButton("왼쪽");
        label=new JLabel("",SwingConstants.CENTER);
        eBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("안녕!");
            }
        });
        wBtn.addActionListener((e)->{
            label.setText("잘가~");
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("종료됩니다~");
            }
        });

        this.add(label);
        this.add(eBtn, BorderLayout.EAST);
        this.add(wBtn, BorderLayout.WEST);
        this.setBounds(0,0,300,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//closing 재정의
    }
    public static void main(String[] args) {
        H16Swing9_10 frame=new H16Swing9_10();
        frame.setVisible(true);

    }
}

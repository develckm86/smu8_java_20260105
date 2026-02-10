package com.smu8.javautil;

import javax.swing.*;
import java.awt.*;

public class L29StopWatch extends JFrame {
    JButton resetBtn;
    JButton startBtn;
    JButton stopBtn;
    JLabel timeLabel;
    long time=0;
    public L29StopWatch(){
        super("스탑워치");
        resetBtn=new JButton("리셋");
        startBtn=new JButton("시작");
        startBtn.setPreferredSize(new Dimension(0,70));//가로 0 은 적용되지 않음 (보더레이아웃은 가로를 100%)
        stopBtn=new JButton("멈춤");
        stopBtn.setPreferredSize(new Dimension(0,70));

        String timeStr=String.format("%.2f",time/100.0); //0 ->0.00

        timeLabel=new JLabel(timeStr,SwingConstants.CENTER); //time
        timeLabel.setFont(new Font("dialog",Font.BOLD,40));
        this.add(timeLabel,BorderLayout.CENTER);
        this.add(startBtn, BorderLayout.NORTH);
        this.add(stopBtn, BorderLayout.SOUTH);
        this.add(resetBtn,BorderLayout.EAST);
        this.setBounds(-1980,0, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new L29StopWatch();
    }
}

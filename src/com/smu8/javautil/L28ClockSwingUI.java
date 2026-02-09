package com.smu8.javautil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class DigitalClock extends JFrame {
    JLabel dateLabel; //날찌출력라벨
    JLabel clockLabel;//시간출력
    JLabel tempLabel;//온도출력
    JLabel humLabel;//습도출력
    JPanel p; //온도와 습도를 포함하는 패널
    public DigitalClock(){
        Font font=new Font("Dialog",Font.BOLD,25);
        dateLabel=new JLabel("2026년 02월 09일 (월)",SwingConstants.CENTER);
        dateLabel.setFont(font);
        clockLabel=new JLabel("13시 35분 07초",SwingConstants.CENTER);
        clockLabel.setFont(font);
        clockLabel.setPreferredSize(new Dimension(0,40));
        tempLabel=new JLabel("온도 27도",SwingConstants.CENTER);
        tempLabel.setFont(font);
        humLabel=new JLabel("습도 50%",SwingConstants.CENTER);
        humLabel.setFont(font);
        p=new JPanel();
        p.add(tempLabel);
        p.add(humLabel);
        this.add(dateLabel, BorderLayout.NORTH);
        this.add(clockLabel);
        this.add(p,BorderLayout.SOUTH);
        ///시간을 1초에 한번씩 바꾸기
        //while (true){}
        Thread clockThread=new Thread(()->{
            DateTimeFormatter dtf=DateTimeFormatter.ofPattern("HH시 mm분 ss초"); //"HH:mm:ss.S" =>  H시M분ss초
            while (true){
                threadSleep(1000);
                LocalTime time=LocalTime.now();
                //System.out.println(time);
                clockLabel.setText(time.format(dtf));
            }
        });
        clockThread.start();

        new Thread(()->dateThread()).start();

        this.setBounds(0,0,400,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    void dateThread(){
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yy년 MM월 dd일 (E)", Locale.KOREAN);
        while (true){
            threadSleep(1000);
            LocalDate now=LocalDate.now();
            dateLabel.setText(now.format(dtf)); //2026-02-09 : yyyy-MM-dd => yy년 MM월 dd일
        }
    }
    void threadSleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
public class L28ClockSwingUI {
    public static void main(String[] args) {
        new DigitalClock();
    }
}

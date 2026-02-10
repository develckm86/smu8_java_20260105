package com.smu8.javautil;

import javax.swing.*;
import java.awt.*;

public class L29StopWatch extends JFrame {
    JButton resetBtn;
    JButton startBtn;
    JButton stopBtn;
    JLabel timeLabel;
    long time=0;
    boolean isRun=true;
    WatchThread watchThread=null;

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

        //boolean isRun=true; //지역변수
        //지역변수를 람다식으로 참조하면 capture 복사해오기 때문에 상수로 변경
        startBtn.addActionListener((event)->{ //객체
            //Thread 문제 : btn 의 무한 반복문에 Thread가 빠져나오지 못해서 GUI 동작이 멈춤
            if(watchThread==null || !watchThread.isAlive()){ //스레드가 계속 생성되는 것을 막음
                watchThread=new WatchThread();
                watchThread.start();
            }
        });
        stopBtn.addActionListener((event)->{
            watchThread.interrupt(); // 스레드 강제 종료 (** 종료된 스레드는 다시 사용불가)
        });

        resetBtn.addActionListener((event)->{
            stopBtn.doClick(); //클릭으로 강제 이벤트 발생
            time=0;
            String strTime=String.format("스톰워치 %.2f",time/100.0);
            timeLabel.setText(strTime);
        });
        this.setBounds(-1980,0, 400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    class WatchThread extends Thread{
        @Override
        public void run() {
            while (isRun){
                //if(!isRun) continue;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                time+=1;
                String strTime=String.format("%.2f",time/100.0);
                timeLabel.setText(strTime);
            }
        }
    }




    public static void main(String[] args) {
        new L29StopWatch();
    }
}

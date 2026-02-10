package com.smu8.javautil;

import java.time.LocalTime;

class ClockThread extends Thread{
    @Override
    public void run() { //새로 만들어질 스레드에게 할일을 지정 (콜백함수)
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(LocalTime.now());
        }
    }
}
class Thermo implements Runnable{
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("현재 온도는 27도");
        }
    }
}
public class L27Clock {
    static void hygro(){
        while (true){
            try {
                Thread.sleep(1000);
                System.out.println("현재 습도는 55%");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread clockThread=new ClockThread();
        clockThread.start(); //스레드 생성후 재정의한 run을 실행 시킴
        //System.out.println("스레드가 2개면 실행됨");
        Thread thermo =new Thread(new Thermo());
        thermo.start();

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                hygro();
            }
        }).start();
        */
        Thread hygro =new Thread(()->{
            hygro();
        });

        hygro.start();
        /*
        while (true){ //Thread 클래스를 재정의
            System.out.println(LocalTime.now());
            Thread.sleep(1000);
        }
        while (true){ //Runnale 재정의
            System.out.println("현재 온도와 습도는 ..");
        }
         */
    }
}

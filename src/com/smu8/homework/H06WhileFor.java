package com.smu8.homework;
import java.util.Scanner;
//String, int, boolean 기본라이브러리 외의 라이브러리 사용시 import (다른패키지)
//java.util : 개발자에게 유용한 라이브러리의 집합(패키지)

public class H06WhileFor {
    public static void main(String[] args) {
        //반복문 2개 while for
        boolean f=true;
        //안녕을 5번만 출력
        int n=0;
        while (f){
            n++;
            System.out.print("안녕!!,");
            if(n==5){
                //break;
                f=false;
            }
        }
        //0~4까지 출력
        //for(int i) : for 의 내부에서만 사용되는 변수 (지역변수)
        for(int i=0; i<=4; i++){
            System.out.print(i);
        }
        //System.out.println(i); //i는 지역변수기 때문에 전역에서 쓸수 없다.
        //5~1까지 출력
        System.out.println("\n5~1까지 출력");
        for(int i=5; i>0; i--){ //상단의 i가 지역변수기 때문에 다른 지역에서도 선언가능
            System.out.print(i);
        }
        //1~10중 짝수만 출력
        System.out.println("\n1~10중 짝수만 출력 : i를 짝수로 증가");
        for (int i=2; i<=10; i+=2){
            System.out.print(i);
        }
        System.out.println("\n1~10중 짝수만 출력 : 짝수가 아닌 i는 건너뜀 continue");
        for (int i=1; i<11; i++){
            if (i%2==1) continue;
            System.out.print(i);
        }

        System.out.println("\n정수를 입력하세요");
        //반복만 이유 : 입력대기 (네이버검색)
        //Scanner : 입력을 대기하는 반복문 (입력할때까지 꼐속 대기)
        //객체 new Scanner(입력받을곳)
        Scanner sc=new Scanner(System.in); //콘솔에서 입력받을 준비
        int inputNum=sc.nextInt(); //콘솔에서 입력하는것을 대기하는 반복문 생성

        System.out.println("입력완료! :"+inputNum);
        for (int i=0; i<inputNum; i++){
            System.out.print("안녕!"+i);
        }

    }
}

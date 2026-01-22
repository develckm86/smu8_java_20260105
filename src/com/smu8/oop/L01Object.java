package com.smu8.oop;

import java.util.Random; //남의 클래스를 사용 => lib 라이브러리 (도구 클래스)

public class L01Object { //클래스의 명명법 : 파스칼 규칙
    int a=10; //필드 (==전역변수)
    public L01Object(){
        //생성자 : 생성자 호출시 객체가 반환
        //객체 생성시 필드를 초기화
    }
    public L01Object(int a){
        //이름이 같은데 다른 기능 : overloading 오버로딩
        //오바로딩을 하려면 매개변수가 달라야합니다.
        //1개의 이름으로 여러 역할 => 다형성 (사람이 생각하는 것 처럼 프로그래밍)
        //this(); 생성자
        //this.sum(); 함수
        this.a=a;
        //this: 나 자신, 필드접근시 사용 (생성자,메소드도 접근 가능)
    }
    //4개의 정수를 받아서 4개의 정수의 합을 반환
    public int sum(int a,int b,int c,int d){ //매개변수 (a,b,c,d)
        int result=0;
        result=a+b+c+d;
        return result;
    }

    //public class : 다른 클래스에서 사용가능(import)
    public static void main(String[] args) {
        //Object Oriented Programming 객체지향 프로그래밍 (객체지향문법) OOP
        //객체를 정의하는 문법
        //객체는 자료형 (==class)
        Random random=new Random();
        L01Object l01Object=new L01Object();
        //new 연산자로 생성자를 호출하면 객체를 반환 => 객체가 생성
        //변수는 항상 같은 타입의 데이터(기본,객체)를 참조합니다.
        //random=10;
        //int i=new Random();
        System.out.println(l01Object.a); //10
        l01Object=new L01Object(100);
        System.out.println(l01Object.a); //100
        int sum=l01Object.sum(11,22,33,44); //매개변수가 4개면 전달인자도 4개
        System.out.println(sum);
    }
}

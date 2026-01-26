package com.smu8.oop;

//class 데이터가 아니다 (객체의 형type)
class StaticTest{
    int a=10;
    void call(){
        System.out.println("안녕!");
    }
}
class StaticTest2{ //class는 데이터가 이니다.
    static int a=100; //static으로 선언된 필드나 함수는 class와 별개로 데이터로 존재
    static void call(){
        System.out.println("잘가~");
    }
}
public class L09Static {
    //main : jvm 호출
    //public : jvm은 오픈
    //static : jvm 은 이미 생성됨
    //void : 어플은 실행이 반환
    //String[]args : 프로그램 실행시 필요한 초기 조건
    public static void main(String[] args) {
        //System.out.println(StaticTest.a); // class는 데이터가 아니기 때문 (오류)
        //StaticTest.call();
        StaticTest s=new StaticTest(); //class로 객체 생성(데이터)
        System.out.println(s.a);
        s.call();

        //static 필드와 메소드는 객체생성 없이 호출 가능!
        System.out.println(StaticTest2.a);
        StaticTest2.call();
    }
}

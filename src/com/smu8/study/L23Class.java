package com.smu8.study;

import java.util.Date;

public class L23Class {
    public static void main(String[] args) {
        //데이터 : 기본형(수,원시형),자료형(복합적,참조형,인스턴스)
        int i=10; //byte short int long
        double d=10.0; //float double
        char c='c';
        boolean b=true;
        //기본형 : 리터럴하게 표기 (보이는 그대로),소문자로 타입명시
        //자료형  : new 연산자로 생성자를 호출, 파스칼규칙(class)
        Date date=new Date(); //new Date(); 객체(Object,Instance) => 객체의 타입이 자료형, 참조형
        System.out.println(date.toLocaleString()); //2026. 1. 15. 오전 10:48:33
        //System.out.println(i.); //기본형은 수만 존재하는 데이터기 때문에 다른자료를 참조하지 않는다!
        //참조형 : 여러데이터를 참조하는 것
        //참조형 : 조수(식별자)만 존재
        new Student();
        //데이터 재사용! => 변수
        var s=new Student();
        s.hello();
        System.out.println(s);//com.smu8.study.Student@4d405ef7  (해당객체의 타입@주소)
        //참조형은 참조하는 데이터가 많아서 객체가 무엇인지 설명하기 곤란 => 객체의 식별자인 주소만 출력 (참조형이다)
        System.out.println(i); //int i
        //기본형은 저장된 데이터가 수 즉 원시데이터기 때문에 설명가능 => 주소가 아닌 데이터가 출력 (기본형이다.)
        Student s2=new Student();
        s2.name="최경민";
        s2.hello();

    }
}
//main 을 실행하면 main이 포함된 패키지를 모두 로딩 후 저장 (메소드영역)
//이후에 main코드를 하나씩 실행!
class Student{ //클래스는 파스칼 표기법
    //3가지 요소
    //필드 : 저장할 데이터 (성적,이름,id)
    //함수 : 객체의 기능 (인사)
    //생성자 : 생성할때 호출됨
    String grade="A";
    String name="최경만";
    String id="A640001";
    public Student(){
        System.out.println("Student() 생성자 호출됨!");
    } //기본 생성자
    public void hello(){ //함수는 카멜 표기법으로
        System.out.println(name+"이 인사합니다.꾸벅");
    }

}





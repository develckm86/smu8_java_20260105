package com.smu8.study;

public class L05Variable {
    public static void main(String[] args) {
        //변수와 데이터 타입
        var a = 10; //= 대입연산(대입,참조한다)
        var b = 20;
        //var java10 버전 이상부터 : 어떤 데이터 타입도 참조가능
        //변수 : 데이터를 참조할수 있는 것,(변할수 있는 수 : 다른 데이터를 참조 가능)
        //var a; : a라는 변수를 만들겠다.
        System.out.println(a+b);

        //var a=30; //a는 한개만 만들 수 있다.
        a=30;
        b=40;
        System.out.println(a+b);
        // var로 선언한 변수는 첫음 참조한 데이터 타입만 참조 가능
        //a="안녕";
        //b="하세요!";
        var c="안녕";
        var d="하세요!";
        System.out.println(c+d);//문자열은 + 연산이 가능

        //자바에서 일반적으로 변수를 선언하는 방법
        //데이터 : 기본형(수)=>소문자, 자료형(참조형)=>대문자
        int i=10; //var a=10;
        float f=13.14f;
        char ch='a';
        String s="문자열";
        //i=""; //변수는 동일한 타입의 데이터만 참조가능
        System.out.println(s);
        s="안녕" +
                "하세요" +
                "!";
        System.out.println(s);
        /*
        s="안녕
           하세요
           !";
        */
        s="안녕\n하세요\n!";
        System.out.println(s);
        //java15  """ """ :여러줄 문자열
        s= """
           오호~
           여러줄이 
           작성 됩니다.
           쓰기 편해요~
           """;
        System.out.println(s);
        //변수를 사용하는 이유 : 재사용
        System.out.println(4*3.141421);
        System.out.println(5*3.14);
        System.out.println(6*3.141);
        double pi=3.141592653589793;
        System.out.println(7*pi);
        System.out.println(8*pi);
    }
}

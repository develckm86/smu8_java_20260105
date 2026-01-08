package com.smu8.study;

public class L03Function {

    public static void a(){// a : 개발자가 작성한 함수의 이름 (시그니처)
        //함수는 실행의 집합
        System.out.println("a 함수 입니다!!");
        System.out.println("안녕 ~");
        System.out.println("잘가 ~");
    }
    //public static void a(){} //함수의 이름은 중복될 수 없다.(오버로드제외)

    public static void sum(int a, int b, int c){//a,b,c 매개변수
        System.out.println("sum : a+b+c 의 결과");
        System.out.println(a+b+c);
    }
    //main 함수 (실행==어플실행)
    public static void main(String[] args) {
        a(); //L03Function 클래스 내부에서 a를 호출하기 때문에 생략가능
        System.out.println("-----------------------------------------");
        L03Function.a();
        // "문자열" 1, 1.3 (연산 +-*/)
        System.out.println(111*111); // 정수 int
        System.out.println(111.1*111.1); // 실수 float

        //L03Function.sum(); //sum 함수는 정수 데이터를 3개 받아야 실행가능
        L03Function.sum(10,20,30); // Parameter(전달인자)==매개변수
        //sum 함수를 실행하는데 10,20,30을 전달했다
        //데이터에는 여러 종류가 있다. -> ( 데이터(초급,고급) ,변수 ) 내일
        System.out.println("String 문자열");
        System.out.println('a'); //문자 char

    }
}

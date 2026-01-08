package com.smu8.study;

public class L07Integer {
    public static void main(String[] args) {
        //정수 : 소수점이 없는 수 -22,0,122
        //정수 기본형 데이터 타입 (정수 데이터를 참조하는 타입)
        // byte : 1byte 크기의 정수 -128~127
        // short : 2byte -32768~32767
        // int : 4byte -21~2147483647 ***정수를 입력하면 int
        // long : 8byte
        byte b=111; //1byte 256 의 경우의 수
//        b=256;
        b=127;
        b=-128;
        short s=32767;
        int i=2147483647;
        long l=12345678901L;

        System.out.println(333); //크기는 short지만 정수를 입력하면 무조건 int

        //정수는 수학적 연산이 가능 (+-*/%)
        System.out.println(i*s);
        System.out.println(i-s);
        System.out.println(10%3); //10/3 10을 3으로 나눈 나머지
        System.out.println(10/4); //10/2 2.5인데 2 (정수간의 연산을 정수를 반환)
        System.out.println(10/4.0); //10/2 2.5인데 2 (정수간의 연산을 정수를 반환)

    }
}

package com.smu8.study;

public class L06Constant {
    public static void main(String[] args) {
        int i=10;
        i=20;
        i=30;
        //변수 : 계속 다른 데이터를 참조할 수 있는
        //상수 : 바뀌지 않는
        final int a=100;
        //a=200; : 컴파일 오류
        final double PI=3.14; //이건 상수야 개발자에게 알려주기 위해 대문자+스테이크문법
        //pi=33.14; //원주율은 바뀔 수 없다.

        //변수와 상수의 표기법
        int kmScore=95; //카멜표기법 (자바의 변수는 대부분 카멜)
        int km_score=95; //스네이크문법(소문자,파일이름,파이썬변수,폴더명)
        final int KM_SCORE=95; //대문자 스네이크 문법 (모든 프로그램밍 언어에서 상수로 사용)

        //잘못사용한 예시
        final int Km_Score=95;//x 스네이크문법은 대문자로만 사용하거나 소문자로만 사용!!
        //윈도우에서 파일명이나 폴더명을 대소문자+언더바를 입력 (os가 대소문자 구분을 잘 못함)
        int KmScore=95; //파스칼표기법 (class명 에서만 사용 x)
        //int int=10; //int,double,class, if, while 예약어는 이름으로 사용 불가
        //int class=10;
        //int public=10;
        //int static=10;
        int 경민의성적=95; //영어로만 사용하기를 권장함
    }
}

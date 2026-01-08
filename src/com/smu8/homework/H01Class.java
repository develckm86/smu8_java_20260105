package com.smu8.homework;

public class H01Class {
    public static void multi(int a, int b,int c){
        System.out.println("a*b*c="+(a*b*c));
    }
    public static void main (String[] args){
        System.out.println("안녕");
        System.out.println(111);
        //print() 함수가 여러 데이터(String,int ...)의 매개변수
        //를 전달받도록 정의되어서 여러 데이터로 출력가능
        multi(11,22,33);
    }
}

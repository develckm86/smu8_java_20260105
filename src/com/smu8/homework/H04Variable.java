package com.smu8.homework;

public class H04Variable {
    public static void main(String[] args) {
        //문제1
        System.out.println("------문제1번-----");
        System.out.println(123); //int 정수
        System.out.println(123.0); //double 실수
        System.out.println('a'); //char 문자
        System.out.println("a"); //String 문자열
        System.out.println('a'=='a'); //97 == 97
        System.out.println('a'=='b');
        System.out.println("------문제2번-----");

        int a;
        a=10;
        //int a=10;
        System.out.println(a);
        a=20;
        System.out.println(a);
        a=30;
        System.out.println(a);
        System.out.println("-----문제3-----");
        //var : 변수를 만들때(선언) 타입을 작성(명시)하지 않음
        var b=10; //b의 타입이 대입한 데이터의 타입으로 변경
        //b="";
        var c="안녕";
        //문자열+정수 =>문자열
        System.out.println(b+c);
        System.out.println(c+b);
        System.out.println("-----문제4-----");
        String x="Hello";
        String y="Java";
        String z="";
        //char z='\0';
        int w=10;
        int q=20;
        System.out.println(x+y);
        System.out.println(x+w);
        System.out.println(w+q+x);
        System.out.println(w+z+q+x);
        System.out.println(w+(q+x));

        System.out.println('a'+'b'); //"ab"x =>195

        final double PI=3.14159;
        int r=5;
        System.out.println(r*2*PI);
        r=10;
        System.out.println(r*2*PI);
        //pi=3.141;
        System.out.println(0.1+0.2);
        //0.30000000000000004

        int studentScore=99;
        final int MAX_SCORE=95;
        boolean isLogin=true;
        byte d=-128; //-128~127
        short e= 31222;
        int f=2147483647;
        long g=1000000000000000000L;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(f+1);
        //2147483649 => -2147483648

        byte h=(byte)128;
        System.out.println(h);
        System.out.println(123-2);
        System.out.println(123*2);
        System.out.println(123/2);
        System.out.println(123%2);

        System.out.println(10/4); //2.5=>2
        System.out.println(10/4.0); //2.5

        double pi=3.14159;
        r=3;
        System.out.println(r*r*pi);
        r=4;
        System.out.println(r*r*pi);
        r=5;
        System.out.println(r*r*pi);
//        System.out.print("학생의 점수는 최대점수보다 큰가요?");
        boolean isMax=studentScore>MAX_SCORE;
        String msg="학생의 점수:"+studentScore+", 비교결과 :"+isMax;
        System.out.println(msg);
        //== !=  >(초과) <(미만) >=(이상) <=(이하)



    }
}

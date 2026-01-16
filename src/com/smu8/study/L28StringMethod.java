package com.smu8.study;

import java.util.Arrays;
import java.util.Locale;

public class L28StringMethod {
    public static void main(String[] args) {
        String s="안녕";
        char [] cArr={'안','녕'};
        //문자로된 열==문자열
        //c==cArr : false
        //자료형간의 == 비교 :두개가 동일한 데이터? 주소가 같나?
        //System.out.println(s==cArr);
        String s2="안녕";
        System.out.println(s==s2); //true
        String s3=new String("안녕");
        System.out.println(s==s3);

        //equals : 자료형의 데이터가 값이 같은지 확인
        System.out.println(s.equals(s3));
        s="hello";
        s3=new String("hello");
        System.out.println(s==s3);
        System.out.println(s.equals(s3));

        System.out.println("hello".equals("Hello"));
        System.out.println("hello".equalsIgnoreCase("Hello"));
        //대소문자 무시

        s="hello";
        System.out.println(s.toUpperCase());//UpperCase:대문자
        s=s.toUpperCase();
        System.out.println(s);
        System.out.println(s.toLowerCase());

        String [] strArr=s.split("");
        System.out.println(Arrays.toString(strArr));
        s="010-1234-5678";
        String [] phoneNumArr=s.split("-");
        System.out.println(Arrays.toString(phoneNumArr));

        s="안녕하세요 최경민입니다. 자바수업입니다.";
        System.out.println(s.contains("최경민"));
        System.out.println(s.indexOf("최경민"));
        System.out.println(s.contains("최경만"));
        System.out.println(s.indexOf("최경만")); //-1
        System.out.println(s.substring(6,9));
    }
}

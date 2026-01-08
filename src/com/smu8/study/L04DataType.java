package com.smu8.study;

public class L04DataType {
    public static void main(String[] args) {
        //data : 기본형, 참조형(자료형)
        //기본형 데이터 : 기본적인 데이터 (컴퓨터 : 수로된 데이터,문자=>수(문자표의 번호를 참조))
        //참조형(자료형) 데이터 : 참조(자식이 있는,필드접근자)하는 데이터가 있는

        System.out.println("안녕하세요!"); //자료형 문자열 String
        System.out.println('a'); //문자 char (Character)
        System.out.println((int)'a'); //문자 char (Character)
        System.out.println((int)'A'); //문자 char (Character)
        System.out.println((int)'안'); //문자 char (Character)
        //자바는 문자 2byte=>16bit  utf(유니코드)-16  (utf-8,utf-16 은 국제표준 문자표로 서로 호환가능)
        //->왜 아스키코드를 만들었나요??=>컴퓨터가 수만 처리할 수 있기 때문에 문자를 수로 표현
        // 이미지,영상,소리 ... 세상의 모든 것을 데이터화할 수 있다.
        System.out.println(137); //int 4byte
        System.out.println(1234567890123456789L); //long 8byte
        System.out.println(13.121212); //실수 double 8byte
        System.out.println(13.121212F); //실수 float 4byte
        System.out.println(1==1); //== 비교연산 (두개가 같니?) => boolean
        System.out.println(1!=1); //!= : 둘은 다르지?
        //true (1), false (0) : boolean 은 1bit 데이터지만 크기는 1byte(메모리를 바이트로 나누기 때문)

    }
}

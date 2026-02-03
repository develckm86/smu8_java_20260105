package com.smu8.homework;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class H14Exception {
    class A{}
    class B extends A{}
    class C extends A{}
    void castTest(){
        A b = new B();
        A c = new C();
        C castC=(C)b; //ClassCastException
    }
    public static void main(String[] args) {
        //배열,파싱(형변환),casting
        H14Exception ex=new H14Exception();
        //ex.castTest();
        System.out.println("오류 발생시 jvm 종료");
        Object o="문자열";
        //오류인데 if 로 처리할 수 있는것 : 개발자가 처리할 수 있는 오류 (unchecked)
        if(o instanceof Date){
            Date d=(Date) o; //ClassCastException
        }
        //통신 객체 : 개발자가 처리할 수 없는 오류 (예외를 강제 checked)
        Socket socket=new Socket();
        try {
            socket.getInputStream(); //throws IOException 예외 위임
        } catch (IOException e) {
            System.out.println("통신중 에외가 발생!"); //로그, db, 유저에게 알림
        }

        int nums[]={10,20,30};
        //nums[4]=50;//ArrayIndexOutOfBoundsException
        //System.out.println(10/0); //ArithmeticException


    }
}

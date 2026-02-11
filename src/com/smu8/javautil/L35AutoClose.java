package com.smu8.javautil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class L35AutoClose {
    public static void main(String[] args) {
        //try(객체생성) 해당객체의 close 를 finally 에서 자동호출
        //입출력, 통신 => Closeable을 구현하고 있는 모든 클래스가 가능 (스트림)
        try(InputStreamReader isr=new InputStreamReader(System.in)){
            int input=0;
            String str="";
            while ((input=isr.read())!='\n'){ //라인개행이 있을때까지만 입력받겠다.
                str+=(char)input;
            }
            System.out.printf("입력한 문자열 : %s", str);
        } catch (IOException e) {
            e.printStackTrace();
        }//finally isr.close() 실행
    }
}

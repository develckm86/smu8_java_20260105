package com.smu8.javautil;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class L34Reader {
    public static void main(String[] args) {
        //Reader + close
        //입출력은 스트림이 있어서 사용완료시 close 로 닫아야함
        InputStreamReader isr=null;
        try {
            isr=new InputStreamReader(System.in, Charset.defaultCharset());
            //int input=isr.read(); //한글자씩 처리 (인코딩에 맞게 바이트의 수를 정해서 처리)
            //InputStream 1byte, InputStreamReader 한글자(1~4byte)
            //System.out.printf("입력한 정수 %d, 문자 %c",input,(char)input);
            String str="";
            while (true){
                int word=isr.read();
                System.out.print(word+" ");
                str+=(char)word;
                if(word=='\n')break; // 통신의 끝에는 항상 -1 (11111111)
            }
            System.out.println();
            System.out.printf("입력한 문자열 : "+str);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(isr!=null) isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

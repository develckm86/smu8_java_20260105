package com.smu8.javautil;

import java.util.Optional;

public class L15Optional {
    public static void main(String[] args) {
        String str=null;
        //System.out.println(str.indexOf("A")); //NullPointerException
        //unchecked exception 체크하지 않아서 생기는 오류
        if(str!=null){
            System.out.println(str.indexOf("A"));
        }else {
            System.out.println("str에 데이터가 없음");
        }
        //checked 오류 : try catch 가 아니면 찾을 수 없는 오류
        try {
            System.out.println(str.toUpperCase());//NPE
        }catch (NullPointerException e){
            System.out.println("str 존재하지 않습니다. null");
        }

        Optional<String> strOpt=Optional.empty();
        //Optional 은 if 로 null 인지 검사를 권장
        if (strOpt.isPresent()){ //str!=null
            String s=strOpt.get();
            System.out.println(s.toUpperCase());
        }else {
            System.out.println("strOpt 데이터가 null");
        }

        strOpt.ifPresentOrElse((s)->{
            System.out.println(s.toUpperCase()+":ifPresent");
        },()->{
            System.out.println("strOpt 데이터가 null : OrElse");
        });


    }
}

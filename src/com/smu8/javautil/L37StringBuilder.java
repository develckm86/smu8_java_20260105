package com.smu8.javautil;

public class L37StringBuilder {
    public static void main(String[] args) {
        //문자열이 불변성을 갖기때문에 문자열 누적시 문제가 발생
        String str="";
        long start=System.nanoTime();
        for (int i=0; i<1_000_00; i++){
            str+=i;
        }
        long end=System.nanoTime();
        System.out.println(end-start); //1358173792
        //System.out.println(str);

        StringBuilder sb=new StringBuilder(); //""
        start=System.nanoTime();
        for (int i=0; i<1_000_00; i++){
            sb.append(i); // str+=i;
        }
        end=System.nanoTime();
        System.out.println(end-start);
        //문자열 누적 : 1339538458
        //스트링빌더 누적 : 1752958

    }
}

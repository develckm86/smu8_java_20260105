package com.smu8.study;

public class L12Char {
    public static void main(String[] args) {
        char c= 'a';//"" : 큰따옴표, '' :작은따옴표, ``: 백틱 (js)
        System.out.println(c);
        c=77;
        System.out.println(c); //77은 아스키에서 M
        c=17891;
        System.out.println(c); //17891은 utf-16에서 䗣
        //c=1111111; //문자는 16비트 정수기 때문에 더 큰수는 불가
        //c='😇'; //맥 : 컨트롤+커맨드+스페이스,윈도우 : 커맨드+.
        //자바는 고정길이 utf-16이기 때문에 4byte 크기의 이모지는 문자로 사용불가 (문자열로는 사용가능)
        String s="이모지는 4byte기 때문에 문자열로 사용: 🥰"; //Surrogate Pair (서로게이트 페어)
        System.out.println(s);
        c='\u0041'; // 0041(16) == 65(10)
        System.out.println(c);
        c='\u9999'; // 香
        System.out.println(c);
        c='\uA9FC'; //ꧼ177117127
        System.out.println(c);
        System.out.println((int)'가');
        System.out.println(Integer.toHexString((int)'가'));
        System.out.println((int)'0');

        System.out.println(Short.MAX_VALUE); //32767
        //66000
        c=65000;
        //c=-10;

    }
}

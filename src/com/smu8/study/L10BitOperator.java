package com.smu8.study;

public class L10BitOperator {
    public static void main(String[] args) {
        //비트연산자
        // >> << 쉬프트 연산
        int a=8; // 00000 1000(이진수)
        a=a<<1; // 00001 0000(2)
        System.out.println(a);

        a<<=2; // 00100 0000(2)
        System.out.println(a);
        //오른쪽 쉬프트 연산으로 1로 만들어보세요!
        a>>=6;
        System.out.println(a);
        a=29;
        System.out.println(Integer.toBinaryString(a)); // 0001 1101
        //Integer : int를 도와주고 int의 자료형데이터 (랩퍼클래스)
        a>>=2; //0000 0111
        System.out.println(a);

        a=-2;
        System.out.println(Integer.toBinaryString(a));
        //11111111111111111111111111111110
        a>>=2;
        System.out.println(a); //음수의 나머지는 1

        // '~' 보수 not 연산
        // 1111->0000
        // 0100 ->1011

        System.out.println(~5);
        System.out.println(~0);
        System.out.println(~5789102);//-5789103

        //비트 논리연산  &(곱) |(합)
        // 1 * 1 = 1
        // 1 * 0 = 0
        // 1 & 1 = 1
        // 1 & 0 = 0

        // 1 + 1 = 2
        // 1 + 0 = 1

        // 1 | 1 = 1
        // 1 | 0 = 1
        // 0 | 0 = 0

        int i=7; //  0111
        int j=8; //  1000
        System.out.println(i|j); //1111
        j=11; // 1011
        System.out.println(i|j); //1111
        System.out.println(i&j);
        System.out.println(7&8);

    }
}

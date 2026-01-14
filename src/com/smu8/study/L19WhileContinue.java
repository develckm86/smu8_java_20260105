package com.smu8.study;

public class L19WhileContinue {
    public static void main(String[] args) {
        //1~1000까지 합을 구하는데 4와(or) 7의 배수는 제외한다.
        //1+2+3+[4]+5+6+(7)+[8]+9+10+11+[12]+13+(14)........
        //continue : 특정 반복 실행을 건너 띄는 것
        int i=0;
        int sum=0;
        while (i<1000){
            ++i; //증감식이 continue 보다 항상 위에 있어야 무한 반복을 피한다.
            if( i%4==0 || i%7==0 ) continue;
            sum+=i;
        }
        System.out.println(sum);
    }
}

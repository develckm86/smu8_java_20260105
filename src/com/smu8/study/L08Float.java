package com.smu8.study;

public class L08Float {
    public static void main(String[] args) {
        //실수의 종류와 저장되는 원리
        float f=123456789012345678901234567890.0F;
        System.out.println(f);
        //1.2345679E29  =>부동소수점 표기번
        //1.23456789012345678901234567890E29
        //가수부가표현할수 있는 길이를 넘어가서 반올림함
        double d=123456789012345678901234567890.0;
        System.out.println(d);
        //1.2345678901234568E29
        //double의 가수부가 더 크기 때문에 정밀도가 높다
        //면접 : 왜 자바는 실수의 기본형으로 double을 사용하나요?
        //=>float보다 double의 가수부가 커서 정밀도가 높다.

        System.out.println(0.1+0.4); // 1/5+2/5
        System.out.println(0.5+0.25); // 1/2+1/4
        // 0.1을 더하기하면 무한소수가 나오는 이유?
        //-> 0.1을 실수로 변환하면서 0.1의 2진수는 무한 소수기 때문
        System.out.println(0.3==(0.1+0.2)); //false

        f=1E32F;
        d=1E300;
        //실수는 지수부가 있기 때문에 천문학적인 수를 다룰 수 있다.
    }
}

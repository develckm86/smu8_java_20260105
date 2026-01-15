package com.smu8.study;

import java.util.Arrays;

public class L24Array {
    public static void main(String[] args) {
        //5명의 성적 (0~100)
        int [] scores=new int [5]; //{0,0,0,0,0}
        //new : 객체를 만든것 (배열은 객체)
        scores[0]=65;
        scores[1]=77;
        scores[2]=89;
        scores[3]=70;
        scores[4]=100;
        System.out.println(scores); //{65,77,89,70,100} or 주소(o)
        //[I@6acbcfc0
        //[ : Array
        //I : int
        //@6acbcfc0 : 데이터가 저장된 메모리 주소
        System.out.println(Arrays.toString(scores));
        //java.util.Arrays : [] Array를 도와주는 클래스(==유틸 클래스)
        //[65, 77, 89, 70, 100]

        //모든 학생의 성적합, 평균
        int sum=scores[0]+scores[1]+scores[2]+scores[3]+scores[4];
        System.out.println(sum); //학생의 총합
        System.out.println(scores.length); //배열의 길이 (배열에 존재하는 아이템 수)
        System.out.println(sum/scores.length);

        //배열의 리터럴 표기법 (배열은 자료형이지만 리터럴 표기법이 존재)
        // { 데이터, 데이터, 데이터,...}
        //배열의 동일한 타입의 데이터만 저장가능
        double [] nums={ 10.1, 20.2, 30, 40.5};
        System.out.println(nums.length); //4(길이==수)
        System.out.println(nums[3]); //40.5
        System.out.println(nums[2]); //30.0 (30->30.0)형변환
        nums[0]=100.0;
        System.out.println(Arrays.toString(nums));
        //[100.0, 20.2, 30.0, 40.5]

    }
}

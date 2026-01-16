package com.smu8.study;

import java.util.Arrays;

public class L29ArrayFor {
    public static void main(String[] args) {
        //선생님 점수 (정수)
        //int [] : 정수 배열 타입
        int [] scoreArr=new int[7]; // new int[7] : Array 객체를 생성, 길이가 7
        scoreArr[0]=88;
        scoreArr[1]=90;
        scoreArr[2]=75;
        scoreArr[3]=85;
        scoreArr[4]=99;
        scoreArr[5]=100;
        scoreArr[6]=87;
        //scoreArr[7]=87; //ArrayIndexOutOfBoundsException
        System.out.println(Arrays.toString(scoreArr)); //[88, 90, 75, 85, 99, 100, 87]

        //90점 이상의 학생은 몇명일까?
        int cnt=0;
        if(scoreArr[0]>=90) cnt++;
        if(scoreArr[1]>=90) cnt++;
        if(scoreArr[2]>=90) cnt++;
        if(scoreArr[3]>=90) cnt++;
        if(scoreArr[4]>=90) cnt++;
        if(scoreArr[5]>=90) cnt++;
        if(scoreArr[6]>=90) cnt++;
        System.out.println(cnt);
        System.out.println(scoreArr.length); //길이 :7

        cnt=0;
        for (int i=0; i<scoreArr.length; i++){
            //System.out.println(i);
            if(scoreArr[i]>=90) cnt++;
        }
        System.out.println("90점 이상을 받은 학생의 수 : "+cnt);

        int [] numArr={33,88,-24,100,0,56,-88};

        //numArr 에 음수가 몇개가 들어왔나요?
        cnt=0;
        for(int i=0; i<numArr.length; i++){
            if(numArr[i]<0) cnt++;
        }
        System.out.println("음수는 "+cnt+"개 입니다.");
        //리터럴 표기시 변수를 선할때만 가능
        //변수를 재사용하고 싶으면 객체생성 후 사용
        String [] strArr={}; //strArr 변수를 선언(만들때)시 리터럴 표기 가능
        strArr=new String[]{}; //변수를 재사용시는 객체생서후 리터럴 표기 사용
        scoreArr=new int[]{55,99,100,77,120,-7,20}; //100점이 넘는 사람과 점수가 음수인 경우가 포함
        //점수의 모든 합을 구하는데 0~100사이의 점수만 합하세요!
        int sum=0;
        for (int i=0; i<scoreArr.length; i++){
            //if(scoreArr[i]>100 || scoreArr[i]<0) continue;
            if(scoreArr[i]>= 0 && scoreArr[i]<=100) sum+=scoreArr[i];
        }
        System.out.println("전체의 합:"+sum);
    }
}

package com.smu8.oop;

//class Calc{} //com.smu8.oop.Calc 가 이미 존재해서 오류

import java.util.Date;
import java.util.Random;

class Calculator{
    //오버로딩, 이름은 한개인데 역할이 여러개 => 다형성 (객체지향문법특징!)
    public int sum(int a,int b){
        return a+b;
    }
    public int sum(int a, int b, int c){
        return a+b+c;
    }
    int a; //전역변수
    //매개변수가 동일한 것을 여러개 처리하고 싶다면 매개변수를 배열로
    public int sum(int [] arr){
        //{10,11,30,20}
        int sum=0; //지역변수 : 함수가 호출되어야 만들어짐(함수명과는 무관)
        for(int i=0; i<arr.length; i++){
            sum+=arr[i];
        }
        return sum;
    }
    // ... 가변인자
    public int multiple( int... nums){ //== int [] nums
        int mult=1; // 0은 무엇이랑 곱해도 0, 때문에 곱하기의 초기값은 1
        //System.out.println(nums); //[I@5f184fc6   [:Array, I:int, @5f184fc6 주소
        for (int i=0; i<nums.length; i++){
            mult*=nums[i];
        }
        return mult;
    }
}
public class L03MethodArgs {
    public static void main(String[] args) {
        Calculator c=new Calculator();
        int result=c.multiple(10,20);
        System.out.println("10*20의 결과:"+result);
        result=c.multiple(10,20,30);
        System.out.println("10*20*30의 결과:"+result);
        result=c.multiple(10,20,30,40);
        System.out.println("10*20*30*40의 결과:"+result);
        result=c.multiple(10,20,30,40,50);
        System.out.println("10*20*30*40*50의 결과:"+result);


        int [] arr={100,200,121,-300,-17,99};
        int sum=c.sum(arr);
        System.out.println("arr의 아이템의 총합 :"+sum);
        sum=c.sum(new int[]{11,22,33});
        System.out.println("arr의 아이템의 총합 :"+sum);
        //c.sum(10,20,30,40,50,60)
        //c.sum(new int[]{10,200,300})x

        //오늘 날짜 java.util.Date
        java.util.Date now=new Date();
        System.out.println(now.toString());//Fri Jan 23 10:57:50 KST 2026
        String nowStr=now.toLocaleString(); //현재지역에 맞게 문자열로 출력
        System.out.println(nowStr); //2026. 1. 23. 오전 10:58:50

        Random random=new Random(); //랜덤수
        int num=random.nextInt();
        System.out.println(num); //-1882134007
        num=random.nextInt(1,51); //1~50
        //1,51 전달인자
        System.out.println(num);


    }
}

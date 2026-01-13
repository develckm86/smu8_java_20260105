package com.smu8.study;

public class L13If {
    public static void main(String[] args) {
        //어플 : 양수와 음수를 다르게 처리함
        int a=199;
        String msg="a는 음수입니다."; //a의 상태를 메세지를 표현

        if(a>0){
           msg="a는 양수입니다.";
        }

        System.out.println(msg);

        a=188;
        msg=""; //초기값x
        if (a>0){
            msg="양수";
        } else { //그 밖의 조건들 (a>0을 제외한 모든 조건)
            msg="음수";
        }
        System.out.println(msg);
        /// a가 0일때를 찾지 못함
        // 분기가 2개를 초가하는 복잡한 상태를 else if로 처리
        a=-0; //+0, -0 둘다 0
        msg="";
        if(a>0){
            msg="양수";
        }else if(a==0){
            msg="제로";
        }else {
            msg="음수";
        }
        System.out.println(msg);

        //실수에서 -0.0 != +0.0 다르다 하지만 값을 비교하기 때문게 true
        System.out.println(0.0==-0.0);


        //if 실습 중요!!
        int birth=2008;
        int year=2026;
        int age=0;
        msg=""; // 만 19세 이상은 주류구입가능,19세 미만은 주류구입불가

        if(year-birth>=19){
            msg="주류구입가능";
        }else{
            msg="주류구입불가능";
        }
        //2-6 정수 n이 짝수이면 “짝수”,
        // 홀수이면 "홀수"를 출력하는 조건문을 작성하시오.
        int n=7777;
        msg="";
        if(n%2==0){
            msg="n은 짝수";
        }else {
            msg="n은 홀수";
        }
        int score=88;
        msg="";
        if(score>=90){
            msg="score가 우수";
        }else if(score>=70){
            msg="score가 보통";
        }else {
            msg="score가 미흡";
        }
        System.out.println(msg);


        int month=3; //월
        msg="";
        if(month>=3 && month<=5){
            msg="봄";
        }else if(month>=6 && month<=8){
            msg="여름";
        }else if(month>=9 && month<=11){
            msg="가을";
        }else if(month==1 || month==2 || month==12){
            msg="겨울";
        }
        System.out.println(msg);
        msg="";
        //month 1~12
        if(month>=1 && month<=12){
            if(month>=3 && month<=5){
                msg="봄";
            }else if(month>=6 && month<=8){
                msg="여름";
            }else if(month>=9 && month<=11){
                msg="가을";
            }else{
                msg="겨울";
            }
        }else {
            msg="month은 1~12월까지 입니다.";
        }


        char ch='C';
        //65~
        //97~
        msg="";
        if(ch>='A' && ch<='Z'){
            msg="대문자";
        }else if(ch>='a' && ch<='z'){
            msg="소문자";
        }else {
            msg="알파벳이 아닙니다!";
        }
        System.out.println(msg);



    }
}

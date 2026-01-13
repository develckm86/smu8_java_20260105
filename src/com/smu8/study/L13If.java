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




    }
}

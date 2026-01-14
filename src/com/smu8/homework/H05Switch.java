package com.smu8.homework;

public class H05Switch {
    public static void main(String[] args) {
        int score=90;
        String grade="";
        switch (score/10){
            case 10: case 9: grade="A"; break;
            case 8: grade="B"; break;
            case 7: grade="C"; break;
            case 6: grade="D"; break;
            default: grade="F";
        }
        //score/5 로 A+ B+ 점수를 구현하세요!

        int n=13; // +양수 0 -음수
        String msg=(n>0)? "양수" : (n<0)? "음수" : "제로" ;


    }
}

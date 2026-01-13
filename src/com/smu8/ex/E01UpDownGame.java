package com.smu8.ex;

import java.util.Random;
import java.util.Scanner;

public class E01UpDownGame {
    public static void main(String[] args) {
        Random random=new Random(); //랜덤한 수 출력
        int num=random.nextInt(1,51); //1~51

        Scanner scanner=new Scanner(System.in); //콘솔에서 입력하는 값 받기
        System.out.println("랜덤 게임 숫자 맞추기");
        System.out.println("(hint :"+num+")");
        System.out.println("1~50 중에 숫자를 맞추세요");
        int inputNum=scanner.nextInt(); //정수를 입력할때까지 대기 (만약 정수가 아니면 오류발생)
        if(inputNum==num){
            System.out.println("정답입니다.");
        }else {
            System.out.println("오답입니다.");
        }

    }
}

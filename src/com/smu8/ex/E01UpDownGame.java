package com.smu8.ex;

import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class E01UpDownGame {
    public static void main(String[] args) {
        Random random=new Random(); //랜덤한 수 출력
        int num=random.nextInt(1,51); //1~51

        Scanner scanner=new Scanner(System.in); //콘솔에서 입력하는 값 받기
        System.out.println("랜덤 게임 숫자 맞추기");
        System.out.println("(hint :"+num+")");
        System.out.println("1~50 중에 숫자를 맞추세요");

        int inputNum;
        while (true) {
            try {
                inputNum = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("정수만 입력해주세요.");
                scanner.nextLine(); //잘못된 입력 비우기
            }
        }

        if(inputNum==num){
            System.out.println("정답입니다.");
        }else {
            System.out.println("오답입니다.");
        }

    }
}

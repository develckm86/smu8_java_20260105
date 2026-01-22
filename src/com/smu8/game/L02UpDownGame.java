package com.smu8.game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class L02UpDownGame {
    public static void main(String[] args) throws IOException {
        /*
        * 이 프로그램의 실행 흐름은 다음 순서로 진행된다.
        1.게임 안내 문구 출력
        2.랜덤한 정답 숫자 생성
        3.최대 입력 횟수(5번) 설정
        4.반복문을 이용해 숫자 입력 받기
        5.입력값과 정답 비교
        6.Up / Down 힌트 출력
        7.결과(Win / Lose) 출력
        */
        System.out.println("Up Down 게임 (1~50 까지 수를 5번 내로 맞추세요!)");
        Random random=new Random();
        int randomNum=random.nextInt(1,51);//1~50까지 수정에 랜덤하게 1개 뽑는다.
        System.out.println(randomNum);
        boolean isWin=false;
        for(int i=0; i<5; i++){
            Scanner scanner=new Scanner(System.in);
//            int inputNum=scanner.nextInt();
            String inputStr=scanner.next();
            int inputNum=Integer.parseInt(inputStr);
            if (randomNum==inputNum){
                isWin=true;
                break;
            }else if(randomNum>inputNum) {
                System.out.println("Up");
            }else { //randomNum<inputNum
                System.out.println("Down");
            }
        }
        if(isWin){
            System.out.println("Win~ 축하합니다.");
        }else {
            System.out.println("Lose~ 아쉽네요.다시시도하세요!");
        }
    }
}

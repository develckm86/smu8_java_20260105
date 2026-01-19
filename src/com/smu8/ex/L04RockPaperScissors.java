package com.smu8.ex;

import java.util.Random;
import java.util.Scanner;

public class L04RockPaperScissors {
    public static void main(String[] args) {
        // 컴퓨터와 진행하는 가위바위보 게임
        // 1. 가위=0, 바위=1, 보=2 로 하기로 약속한다.
        // 2. 게임은 유저와 컴퓨터 중 3번을 먼저 이길때까지 종료되지 않습니다.
        // 3. 컴퓨터는 0~2까지 수정 1개를 랜덤하게 정한다.
        // 4. 유저에게 0~2를 입력하게 한다.
        // 5. 유저의 입력과 컴퓨터의 입력을 비교한다,
        // 5-1. 유저의 입력과 컴퓨터의 입력이 같으면 2번부터 다시 진행
        // 5-2. 유저의 입력이 컴퓨터의 입력을 이기면(바위 1 vs 가위 0) Win
        // 5-3. 유저의 입력이 컴퓨터의 입력을 지면(가위 0 vs 바위 1) Lose
        //
        final int SCISSORS=0;
        final int ROCK=1;
        final int PAPER=2;
        int userWinCnt=0; //유저가 이긴 수
        int computerWinCnt=0; //컴퓨터가 이긴 수

        System.out.println("가위 바위 보 게임입니다. 3번이겨야 게임을 종료합니다.");

        while (userWinCnt<3 && computerWinCnt<3){ //유저나 컴퓨터가 3번 이길때까지

            int randomNum= new Random().nextInt(0,3); //0~2까지
            Scanner scanner=new Scanner(System.in);//콘솔에서 유저의 입력을 받겠다.
            System.out.print("가위=0 바위=1 보=2 중 한개를 입력 :");
            int inputNum=scanner.nextInt();
            String inputStr=switch (inputNum){
                case ROCK -> "바위";
                case SCISSORS -> "가위";
                case PAPER -> "보";
                default -> throw new IllegalStateException("오류 발생 게임종료! 수는 0~2까지만 입력가능");
            };
            String randomStr=switch (randomNum){
                case ROCK -> "바위";
                case SCISSORS -> "가위";
                case PAPER -> "보";
                default -> throw new IllegalStateException("오류 발생 게임종료! 수는 0~2외의 수가 나옴");
            };

            System.out.print(inputStr+" VS "+randomStr+" : ");
            if(inputNum==randomNum){
                System.out.println("비김");
            }else {
                int result=inputNum-randomNum;
                if(result==1 || result ==-2 ){
                    System.out.println("Win");
                    userWinCnt++;
                }else{
                    System.out.println("Lose");
                    computerWinCnt++;
                }
            }
        }
        String msg=(userWinCnt==3)?"유저 승리":"컴퓨터 승리";
        System.out.println(msg);


    }
}

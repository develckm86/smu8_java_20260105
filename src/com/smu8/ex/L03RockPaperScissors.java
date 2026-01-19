package com.smu8.ex;

import java.util.Random;

public class L03RockPaperScissors {
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
        System.out.println("가위 바위 보 게임입니다. 3번이겨야 게임을 종료합니다.");
        int random= new Random().nextInt(0,3); //0~2까지
        System.out.println(random);

    }
}

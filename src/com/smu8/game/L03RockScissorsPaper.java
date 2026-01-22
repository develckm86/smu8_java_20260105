package com.smu8.game;

import java.util.Random;
import java.util.Scanner;

public class L03RockScissorsPaper {
    public static void main(String[] args) {
        // 가위 바위 보 1판
        //0. 게임 안내문
        //1. 컴퓨터가 가위=0 바위=1 보=2 중 1개를 고름
        //2. 유저에게 0~2사이 수를 입력 받음
        //3. 유저 입력과 랜덤 수 비교
        //4. 이겼나 졌나! 출력
        //5. 1~4번까지를 유저나 컴퓨터가 3번 이길때 까지 반복하세요!
        //6. 매번(반복문 안에서) 생성할 필요없는 객체나 기본형은 밖으로
        System.out.println("가위 바위 보 게임입니다. 0~2까지 수만 입력");
        String [] gameArr={"가위","바위","보"};

        Random random =new Random();
        Scanner scanner=new Scanner(System.in);

        int userCnt=0; //유저가 이긴수
        int comCnt=0; //컴퓨터가 이긴수
        while (true){
            int randomNum=random.nextInt(3); //0~2
            //System.out.println(gameArr[randomNum]);
            System.out.print("가위=0 바위=1 보=2 : ");
            int inputNum=scanner.nextInt();
            System.out.print(gameArr[inputNum]+" VS "+gameArr[randomNum]+" : ");
            if(inputNum-randomNum == 0){
                System.out.println("비김");
            }else if( inputNum-randomNum==1 || inputNum-randomNum==-2){
                System.out.println("이김!");
                if(++userCnt==3) break;
            }else {
                System.out.println("졌다!");
                if(++comCnt==3) break;
            }
        }
        if(userCnt==3){
            System.out.println("최종 승자 유저!");
        }else {
            System.out.println("최종 승자 컴퓨터!");
        }
    }
}

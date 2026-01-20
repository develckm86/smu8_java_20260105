package com.smu8.ex;

import java.util.Random;
import java.util.Scanner;

public class E02UpDownGame {
    public static void main(String[] args) {
        System.out.println("Up Down 게임 1~50까지 수를 입력하세요!(기회는 5번)");

        Random random=new Random(); //랜덤한 수 출력
        int num=random.nextInt(1,51); //1~50

        //System.out.println("(hint :"+num+")"); //정답힌트
        final int MAX_COUNT=5; //기회 (입력받는 최대 수)
        int count=0;  //입력받은 개수
        boolean result=false;

        while (count++<MAX_COUNT){ //5번까지 입력 받겠다.

            System.out.print(count+"번째 기회 :");
            Scanner scanner=new Scanner(System.in); //콘솔에서 입력하는 값 받기
            int inputNum=scanner.nextInt(); //정수를 입력할때까지 대기 (만약 정수가 아니면 오류발생)

            if(inputNum==num){//정답
                System.out.println("정답입니다.");
                result=true; //정답
                break; //정답인 경우 입력받기 종료
            }else {//오답
                String msg=(inputNum>num)?"Down": "Up";
                System.out.println("오답 "+msg);
            }
        }

        if(result){
            System.out.println("--------------Win 축하합니다.-------------");
        }else {
            System.out.println("--------------Lose 다음기회에-------------");
        }
    }
}

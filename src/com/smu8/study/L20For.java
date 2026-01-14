package com.smu8.study;

public class L20For {
    public static void main(String[] args) {
        //1~10까지 수를 출력
        /*System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        */
        int n=0;
        while (n<10){
            n++;
            System.out.print(n);
            if(n!=10)System.out.print(",");
        }
        System.out.println("\nfor 출력 1~10");
        for(int i=1; i<11; i++){
            System.out.print(i+",");
        }
        //10 ~ 1 for로 거꾸로 출력
        System.out.println("\nfor 출력 10~1");
        for(int i=10; i>0; i--){
            System.out.print(i+",");
        }
        //1~20 까지 출력하다가 6의 배수는 제외하세요.
        // 1,2,3,4,5,7,8,9,10,11,13....
        System.out.println("\nfor 출력 1~20 6의배수 제외");
        for (int i=1; i<=20; i++){
            if(i%6==0) continue;
            System.out.print(i+",");
        }

        System.out.println("\n1~10000까지의 누적합 구하던 중 20000 이상일때 중지");
        //1~10000까지 중첩합을 구합니다...총합이 20000 이상이 될때 멈추세요
        int sum=0;
        for (int i=1; i<=10000; i++){
            sum+=i;
            if(sum>=20000){
                System.out.println(i+"번째에 끝");
                break;
            }
        }
        System.out.println("1~10000까지의 누적합 : "+sum);
    }
}

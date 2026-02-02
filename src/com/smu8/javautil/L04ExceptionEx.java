package com.smu8.javautil;

import java.util.Scanner;

public class L04ExceptionEx {
    public static int parseAge(String birth){
        int age=0;
        return age;
    }
    public static void main(String[] args) {
        //콘솔에서 (태어난 해)입력 => 나이를 알려주는 어플
        Scanner scanner=new Scanner(System.in);
        System.out.println("나이를 알려주는 어플");
        while (true){
            System.out.print("당신의 태어난 해(19**,20**):");
            String birthStr=scanner.next();
            int year=2026;
            try {
                int birth=Integer.parseInt(birthStr);
                if(birth<1900 || birth>year) throw new IllegalArgumentException("태어난 해는 1900~"+year);
                System.out.println("당신의 나이는 :"+(year-birth));
            }catch (NumberFormatException e){
                System.out.println("수만 입력하세요!");
            }catch (IllegalArgumentException e){
                System.out.println("입력오류 :"+e.getMessage());
            }
        }
    }
}

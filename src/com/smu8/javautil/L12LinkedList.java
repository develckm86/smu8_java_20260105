package com.smu8.javautil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L12LinkedList {
    public static void main(String[] args) {
        List<String> strList=new LinkedList<>();
        strList.add("안녕");
        strList.add("잘가");
        System.out.println(strList);
        //링크드리스트는 사용법이 다르지 않음!!
        List<Integer> numArrList=new ArrayList<>();
        List<Integer> numLinkedList=new LinkedList<>();
        long start=System.nanoTime(); // 1/1_000_000 초 == 나노초
        for (int i=0; i<10_000_000; i++){
            numArrList.add(i);
            numLinkedList.add(i);
        }
        long end=System.nanoTime();
        System.out.println((end-start)/1_000_000.0+"초");
        //System.out.println(numLinkedList);
        //System.out.println(numArrList);
        start=System.nanoTime();
        numArrList.remove(0); //0.327584초
        end=System.nanoTime();
        System.out.println((end-start)/1_000_000.0+"초");

        start=System.nanoTime();
        numLinkedList.remove(0); //0.009792초
        end=System.nanoTime();
        System.out.println((end-start)/1_000_000.0+"초");

    }
}

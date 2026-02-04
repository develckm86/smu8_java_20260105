package com.smu8.javautil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class L11ListIterator {
    public static void main(String[] args) {
        List<String> strList=new ArrayList<>(Arrays.asList("사과","바나나","키위","망고"));
        //인덱스 기반 반복문
        System.out.print(strList.get(0));
        System.out.print(strList.get(1));
        System.out.print(strList.get(2));
        System.out.print(strList.get(3));
        System.out.println("\nfor 인덱스 반복문");
        for(int i=0; i<strList.size(); i++){
            System.out.print(strList.get(i)+",");
        }
        //반복자 기반 반복문 (향상 반복문)
        System.out.println("\nIterator while 반복문");
        Iterator<String> it=strList.iterator();//반복자 생성
        while (it.hasNext()){
            String fruit=it.next();
            System.out.print(fruit+",");
        }
        System.out.println("\nIterator 향상된 for 반복문");
        //: strList 이터레이터 생성
        for(String fruit : strList){
            System.out.print(fruit+",");
        }
        System.out.println("\nIterator each 내부 반복문(콜백)");
        Consumer<String> callBackFunc=(fruit)->{
            System.out.print(fruit+",");
        };
        strList.forEach(callBackFunc);

        System.out.println("\nIterator each 내부 반복문(콜백) 코드 줄임");
        strList.forEach((fruit)->{
            System.out.print(fruit+",");
        });


    }
}

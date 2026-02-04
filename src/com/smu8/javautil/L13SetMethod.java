package com.smu8.javautil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class L13SetMethod {
    public static void main(String[] args) {
        Set<String> fruitSet=new HashSet<>();
        fruitSet.add("바나나");
        fruitSet.add("바나나");
        fruitSet.add("사과");
        fruitSet.add("망고");
        fruitSet.add("애플망고");
        fruitSet.add("두리안");
        fruitSet.add("두리안");
        System.out.println(fruitSet); //[애플망고, 두리안, 망고, 사과, 바나나]
        //fruitSet.get(); //set 은 찾아서 가져오는 것이 힘들다
        boolean isDurian=fruitSet.contains("두리안");
        System.out.println("두리안 있니?"+isDurian);
        System.out.println(fruitSet.size()); //사이즈(크기) == 길이
        System.out.println(fruitSet.isEmpty()); //비었니?

        //집합
        Set<Integer> a=new HashSet<>(Arrays.asList(1,2,3));
        Set<Integer> b=new HashSet<>(Arrays.asList(3,4,5));
        Set<Integer> c=new HashSet<>(Arrays.asList(5,6,7));

        //a.addAll(b); // a 합집합 b
        //System.out.println(a);
        Set<Integer> newA=new HashSet<>(a); //collection 객체 복사
        newA.addAll(b);
        System.out.println(newA);
        System.out.println(a);
        newA.addAll(c);
        System.out.println(newA);//[1, 2, 3, 4, 5, 6, 7]
        a.retainAll(b);
        System.out.println(a); //[3] a 교집합 b
        //집합을 하면 데이터가 변경됨
        b.removeAll(c);
        System.out.println(b); //b-c 차집합

        //Set 은 인덱스 기반 반복문 불가능!!
        Iterator<String> it=fruitSet.iterator();
        System.out.println("이터레이터 반복문 while");
        while (it.hasNext()){
            System.out.print(it.next()+",");
        }
        System.out.println("\n이터레이터 반복문 향상된 for");
        for (String fruit : fruitSet){
            System.out.print(fruit+",");
        }
        System.out.println("\n이터레이터 반복문 each 내부반복문");
        Consumer<String> callBackFunc=(fruit)->{
            System.out.print(fruit+",");
        };
        fruitSet.forEach(callBackFunc);

    }
}

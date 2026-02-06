package com.smu8.javautil;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class L21StreamAPI2 {
    public static void main(String[] args) {
        //배열 => 스트림
        String [] strArr={"자바","스트림","반복문","체이닝"};
        //Array 배열은 이터레이터를 포함하고 있지 않음
        //=>향상된 반복문 사용시 반복자 개입
        for (String s : strArr){
            System.out.print(s+",");
        }
        Stream<String> strStream = Arrays.stream(strArr);
        //변수가 참조할 수있지만 스트림은 재사용 불가라 바로 사용하는 것이 일반적
        System.out.println("\n스트림 forEach");
        Arrays.stream(strArr).forEach((s)->{
            System.out.print(s+",");
        });
        List<Integer> numList=new ArrayList<>();
        numList.add(10);
        numList.add(22);
        numList.add(33);
        numList.add(45);
        System.out.println("\n"+numList);
        //모든 Collection은 stream 을 생성할 수 있게 구현됨
        numList.stream().forEach(System.out::println);
        Set<Integer> numSet=new HashSet<>(Arrays.asList(1,1,2,3,3,4,5,5));
        System.out.println(numSet);
        numSet.stream().forEach(System.out::println);
        Map<String,Object> customer=new HashMap<>();
        customer.put("id","develckm");
        customer.put("name","경민코딩");
        customer.put("grade",1);
        customer.put("isMarried",true);
        System.out.println(customer);
        System.out.println(customer.get("isMarried")); //고객이 결혼했냐??
        //Map은 Collection의 하위클래스가 아니다 : Set을 키로 사용함, EntrySet으로 Set 반환
        customer.keySet().stream().forEach((key)->{
            Object value=customer.get(key);
            System.out.println(key+" : "+value);
        });
        // Set[Entry(key,value),Entry(name,경민코딩),Entry(isMarried,true)...]
        customer.entrySet().stream().forEach((entry)->{
            String key=entry.getKey();
            Object value=entry.getValue();
            System.out.println(key+" == " +value);
        });

        //기본형을 지원하는 스트림 Stream+기본형(int, long, double)
        //콜렉션의 단점 기본형 사용불가를 해결
        //List<Integer>
        //int[] numArr=new int[3];
        LongStream longStream=LongStream.of(10L,20L,222222222222222L);
        IntStream intStream=IntStream.of(10,20,30,40);
        DoubleStream doubleStream=DoubleStream.of(10.0,22.13);
        Stream<Integer> integerStream=Stream.of(10,20,30,40);

        OptionalInt intOpt=OptionalInt.of(13);
        Optional<Integer> numOpt=Optional.of(13);
    }
}

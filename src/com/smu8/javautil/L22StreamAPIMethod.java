package com.smu8.javautil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class L22StreamAPIMethod {
    public static void main(String[] args) {
        //중간연산자 : 다시 Stream 을 반환 (filter,map)
        //최종연산자 : Stream을 반환하지 않는 연산자 (forEach)
        IntStream numStream=IntStream.of(10,-7,20,39);
        //검사 후 새로운 스트림 반환 : filter
        IntStream numStream2=numStream.filter((n)->{
            return n>=0;
        });//Predicate.test
        numStream2.forEach(System.out::println);

        List<Number> numList=new ArrayList<>(Arrays.asList(10, -10.0, 20.12, 13));
        //리스트의 모든 수를 음수로 바꾼다.(Map Function.apply)
        //Math : 연산을 도와주는 클래스
        //abs() : 절대값
        System.out.println(Math.abs(-13));
        Stream<Double>  doubleStream=numList.stream()
                .map((n)->- (Math.abs(n.doubleValue()) ));
        doubleStream.forEach(System.out::println);

        //모든 수를 정수(int)로 바꾼후 출력!
        numList.stream()
                .map((n)->n.intValue())
                .forEach((i)->{System.out.println(i);});
        //모든 실수로 바꾼 후 출력 (함수참조를 사용해서!)

        numList.stream()
                .map(Number::doubleValue)
                .forEach(System.out::println);

        List<String> strList=new ArrayList<>(Arrays.asList("13","13","11","11","-34","88"));
        //1. 받은 문자열을 수로 변환
        //2. 양수만 필터
        //3. 출력
        strList.stream()
                .map(Integer::parseInt)
                .filter((i)->i>=0)
                .forEach(System.out::println);

        strList.stream()
                .distinct()
                .forEach(System.out::println);
        //strList 의 중복을 제거, 정수로 변환, 제곱의 값으로 바꾸고 출력
        strList.stream()
                .distinct()
                .map(Integer::parseInt)
                .map(i->i*i) //거듭제곱
                .forEach(System.out::println);
        //"13","13","11","11","-34","88"
        strList.stream()
                .limit(5)
                .skip(2)
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }
}

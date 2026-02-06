package com.smu8.javautil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L23StreamAPIMethod2 {
    public static void main(String[] args) {
        //최종연산자 : 더이상 스트림을 반환하지 않아서 체이닝 할 수 없는 것
        //.forEach  : 출력, 로그,최종연산
        List<String> strList=new ArrayList<>(Arrays.asList("1","1","2","2","3","-5"));
        strList.stream().forEach(System.out::println);
        long count=strList.stream().count(); //length,size,count
        System.out.println(count);
        //reduce() : 하나의 결과로 만들때 (전체의합, 전체의 곱)
        Optional<String> resultOpt=strList.stream().reduce((s, s2)->s+s2);
        //reduce 는 null 을 반환할수 있어서 Optional
        String result=resultOpt.orElse("");
        System.out.println(result);
        //문제  위의 문자열을 정수로 바꿔서 누적합을 출력  (중복제거,양수만)
        int sum=strList.stream()
                .distinct()
                .map(Integer::parseInt)
                .filter(n->n>=0)
                .reduce((n,n2)->n+n2)
                .orElse(0);
        System.out.println(sum);
        //검사 anyMatch,allMatch,noneMatch =>boolean
        boolean is=strList.stream()
                .map(Integer::parseInt)
                .allMatch(i->i>=0);
        System.out.println("모두 양이니?"+is);
        //1,1,2,2,3,-5
        Optional<String> strOpt=strList.stream().findAny();
        strOpt.ifPresent(System.out::println);
        //findAny 는 임의 값을 가져오는데 대체로 첫번째 걸 찾아서 반환
        //임의 값을 가져오는 경우는 스트림 여러개가 병렬로 있는 경우.



        //collect -> list,set map 으로 스트림반환

        List nums=Stream.of("12","13","1","-5")
                .collect(Collectors.toList());
        System.out.println(nums.get(1));

        Set numSet=Stream.of(1,1,2,2,3,4,5)
                .collect(Collectors.toSet());
        System.out.println(numSet);
        //"1","1","2","2","3","-5"
        //strList 의 있는 문자열을 더블로 변환(map)뒤 다시 list로 반환
        List<Double> doubleList =strList.stream()
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        System.out.println(doubleList);
        System.out.println(doubleList.get(4));
    }
}

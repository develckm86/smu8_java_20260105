package com.smu8.javautil;

import java.util.*;

public class L08Map {
    public static void main(String[] args) {
        //HashSet,LinkedHashSet,TreeSet
        //HashMap,LinkedHashMap,TreeMap
        //key:value 로 된 데이터가 Map으로 key가 Set으로 되어 있어서 절대 중복되지 않는다.
        Map<String,Object> person=new HashMap<>();
        person.put("이름","최경민");
        person.put("나이",40);
        person.put("이름","경민코딩");
        //키가 중복을 허용하면 이름이 2개, 중복을 허용하지 않으면(Set) 이름이 바뀜
        //키는 Set이기 때문에 equals가 구현된 자료형이거나 기본형으로 작성하세요!
        System.out.println(person);
        //{이름=경민코딩, 나이=40} or { 이름:경민코딩, 나이:40 }

        Set<String> keys =person.keySet(); //key만
        System.out.println(keys); //[이름, 나이]
        Collection<Object> values=person.values();
        System.out.println(values);//[경민코딩, 40]


    }
}

package com.smu8.javautil;

import java.util.*;

public class L14MapMethod {
    public static void main(String[] args) {
        //key value
        Map<String,Object> person=new HashMap<>();
        person.put("id","경민코딩");
        person.put("name","최경민");
        person.put("age",40);
        System.out.println(person);//{name=최경민, id=경민코딩, age=40}
        System.out.println(person.get("id"));
        System.out.println(person.containsKey("id")); // key 가 있니?
        System.out.println(person.containsValue(40));//  value 가 있니?
        person.remove("name");
        System.out.println(person);
        person.put("name","smu8기");
        //person.put("name","kosmo");
        person.putIfAbsent("co","kosmo"); //키가 없을때만 추가
        System.out.println(person);

        Set<String> keys=person.keySet(); //맵이 반복문 사용 불가라 많이사용
        System.out.println(keys);
        System.out.println("Set 을 이용한 Map의 반복문");
        for (String key: keys){
            Object value=person.get(key);
            System.out.print(key+"="+value+",");
        }
        //value 만 보기 (잘사용되지 않음!!)
        Collection<Object> values=person.values();
        System.out.println("\n"+values);

        //EntrySet : Map 의 반복을 위해 나온 타입
        Set<Map.Entry<String,Object>> entrySet=person.entrySet();
        for (Map.Entry<String,Object> entry : entrySet){
            String key=entry.getKey();
            Object value=entry.getValue();
            System.out.println(key+"="+value);
        }
    }
}

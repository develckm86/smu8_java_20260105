package com.smu8.javautil;

import java.time.LocalDate;

class GenericTest<T>{ //<T extends Number> : 수 랩퍼클래스만 가능
    public T o; //null
}

public class L05Generic {
    public static void main(String[] args) {
        GenericTest g=new GenericTest(); //<?> : T 가 Object
        g.o="안녕"; //String
        g.o=13; //Integer
        g.o=true; //Boolean
        g.o= LocalDate.now(); //LocalDate
        //타입다형성
        //장점 : 어떤 타입의 객체든 부모 타입의 변수로 참조가능
        //단점 : 어떤 객체를 참조하고 있는지 파악하기 힘들다!
        //제네릭은 타입의 다형성의 단점 보완
        //제네릭은 랩퍼클래스만 작성가능 (==기본형이 될 수 없다.)
        GenericTest<Integer> g2=new GenericTest<Integer>();
        //g2.o="안녕";
        //g2.o=LocalDate.now();
        g2.o=13;
        //g2.o=true;
        g2.o=(int)13.0;
        System.out.println(g2.o);

    }
}

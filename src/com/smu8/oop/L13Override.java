package com.smu8.oop;

import java.util.Scanner;

class Animal{
    String name="동물";
}
class Dog extends Animal{

}
class Cat extends Animal{
    String name="고양이";
}

class User{
    String name;
    //생성자 작성시 기본생성자는 자동완성되지 않음 !!
    public User(String name){ //생성자 규칙을 이름을 꼭 초기화 하도록 수정
        this.name=name;
    }
    public void say(){
        System.out.println("안녕하세요!");
    }
}
//class Customer extends User{ } //오류 자식이 부모의 기본생성자만 호출하기 때문에 ???
class Customer extends User{
    public Customer(){
        super("고객");
    }
    public Customer(String name){
        //super() (x)
        super(name);
    }
    //오버라이드
    //@ :어노테이션 1.컴파일시 오류인지 검사, 2.컴파일러가 자동완성(**)
    @Override //부모의 함수를 재정의한 것이 맞는지 검사
    public void say(){
        //System.out.println("안녕하세요!");
        //System.out.println("저는 "+super.name+"입니다.");

        System.out.println("저는 "+this.name+"입니다."); //부모 것은 나의 것
        super.say(); //"안녕하세요!"
    }
}



public class L13Override { //올라탄다 ( 부모의 것을 자식이 재정의 )
    public static void main(String[] args) {
        User user=new User("경민");
        System.out.println(user.name);//경민
        //Scanner scanner=new Scanner(System.in); //생성자의 규칙을 강제

        Customer customer=new Customer();
        System.out.println(customer.name); //고객
        customer.say();

        Customer customer2=new Customer("경만코딩");
        System.out.println(customer2.name); //경만코딩
        customer2.say();

        Dog dog=new Dog();
        System.out.println(dog.name); //동물
        Cat cat=new Cat();
        System.out.println(cat.name); //고양이
    }
}

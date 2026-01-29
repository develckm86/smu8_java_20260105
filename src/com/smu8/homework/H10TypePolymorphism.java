package com.smu8.homework;

//강아지와 고양이의 원형이 동물(sound)
//동물원을 만들어서 강아지 고양이의 소리가 나도록 하세요. 만약 강아지면 fetch를 하라
class Animal{
    String sound(){
        return "동물의 울음소리";
    }
}

class Dog extends Animal{
    String name="코에";
    @Override
    String sound(){
        return "멍멍!";
    }
    void fetch(){
        System.out.println("킁킁킁");
    }

    @Override
    public String toString() {
        return "Dog {name="+this.name+"}";
    }
}
class Cat extends Animal{
    @Override
    String sound(){
        return "냐옹~";
    }
}
class Zoo{
    //동물이 수천마리면 동물의 수만큼 makeSound를 만들어야함
    /*
    void makeSound(Dog d){
        System.out.println(d.sound());
    }
    void makeSound(Cat c){
        System.out.println(c.sound());
    }
    */
    void makeSound(Animal a){
        System.out.println(a.sound());
        if(a instanceof Dog d){
            //Dog d=(Dog) a;
            d.fetch();
        }
    }
}
public class H10TypePolymorphism {
    public static void main(String[] args) {
        Dog d=new Dog();
        Cat c=new Cat();
        Zoo zoo=new Zoo();
        zoo.makeSound(d);
        zoo.makeSound(c);
    }
}

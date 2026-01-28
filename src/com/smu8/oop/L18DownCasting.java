package com.smu8.oop;

class Ani{
    String name;
    public Ani(String name){
        this.name=name;
    }
    void sound(){
        System.out.print("동물이 내는 소리!");
    }
}
class Lion extends Ani{
    public Lion(){
        super("사자");
    }
    @Override
    void sound() {
        //super.sound();
        System.out.println("어흥~");
    }
    void run(){
        System.out.println(super.name+"가 달린다");
    }
}
class Penguin extends Ani{
    public Penguin(){
        super("팽귄");
    }
    @Override
    void sound() {
        //super.sound();
        System.out.println("삑삑");
    }
    void swim(){
        System.out.println(super.name+"이 수영한다");
    }
}
public class L18DownCasting {
    public static void main(String[] args) {
//        Lion lion=new Lion();
        Ani animal=new Lion();
        System.out.println(animal.name);
        animal.sound();
        //animal.run(); //부모타입의 필드나 함수만 참조가능
        //Lion lion=animal; //animail 은 Lion이나 Penguin 이 될 수 있어서
        //부자연스러운 것을 강제로 형변환 => casting
        //Lion lion=(Lion) animal; //다운캐스팅
        //lion.run();
        if(animal instanceof Lion lion){ //자동 다운캐스팅 java 21
            lion.run();
        }
        animal=new Penguin();
        System.out.println(animal.name);
        animal.sound();
        //업캐스팅
        //부모 타입의 변수가 자식 객체를 참조하는 것

        //animal.swim();
        if(animal instanceof Penguin){ //animal 이 참조하는 것이 펜귄이면 형변환
            Penguin penguin=(Penguin) animal;
            penguin.swim();
        }

        //Lion lion2=(Lion) animal; //런타임오류 ClassCastException
        //animal Penguin 객체를 참조하는데 Lion 으로 바꾸고 있어서 오류 발생
    }
}

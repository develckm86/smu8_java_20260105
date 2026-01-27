package com.smu8.oop;
//Car 클래스를 만드세요. (name,move()=>"움직인다" 출력)
//Car 클래스는 생성할때 name 을 초기화 함

//ElectricCar 클래스 (int bettery=100) 만들고 Car 를 상속받으세요.
//car.move()를 재정의 할건데 car.move()+(better--) 를 구현하세요.
class Car{
    String name;
    public Car(String name){
        this.name=name;
    }
    public void move(){
        System.out.println(this.name+" 움직인다.");
    }
}

class ElectricCar extends Car{
    //public ElectricCar(){super();} //생성자는 부모의 기본생성자만 호출해서 오류
    private int battery =100;
    public ElectricCar(String name){
        super(name);
    }
    public int getBattery(){
        return this.battery;
    }

    @Override
    public void move() {
        battery--;
        super.move();
    }
}

public class L14ExtendsEx {
    public static void main(String[] args) {
        Car car=new Car("그랜져");
        car.move();
        ElectricCar electricCar=new ElectricCar("테슬라x");
        electricCar.move();
        electricCar.move();
        electricCar.move();
        electricCar.move();
        electricCar.move();
        System.out.println(electricCar.getBattery());

    }
}

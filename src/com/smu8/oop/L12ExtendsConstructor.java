package com.smu8.oop;

class X{
    int a=10;
    public X(){
        System.out.println("X 기본생성자 호출");
    }
}
class Y extends X{
    int b=20;
    public Y(){
        super();
        System.out.println("Y 기본생성자 호출");
    }
    public Y(int b){
        this.b=b;
        System.out.println("Y(int b) 생성자 호출");
    }
}
//extends X,Y : 오류 (다중상속 불가 : 두 클래스의 필드나 함수가 같은 이름일때 어떤것을 사용할지 알수 없어서)
class Z extends Y{
    public Z(){ //기본생성자
        //super(); //생성자에서 부모생성자를 호출하지 않으면 컴파일러가 자동완성!(강제)
        super(200); //부모 생성자를 호출하는 super()를 재정의할 수 있다.
        //super(); //부모는 한번만 호출
        System.out.println("Z 기본생성자 호출"+super.b);
        //super(200); //언제나 부모가 객체로 만들어져야하기 때문에 맨위에만 작성가능
        //부모가 만들어지기 전에 자식이 어떤 코드도 실행할 수 없다.
    }
    int c=30;
}
public class L12ExtendsConstructor {
    public L12ExtendsConstructor(){
    }// 생상자
    //constructor(){}
    public static void main(String[] args) {
        Z z=new Z(); // 부모부터 객체 생성
        System.out.println(z.a); //X.a
        System.out.println(z.b); //Y.b
        System.out.println(z.c); //Z.c
        //Z는 부모인 Y,X 객체로 가진다 (이 객체를 super로 참조)
        //객체를 생성하려면 무조건 생성자가 호출되어야합니다.
    }

}

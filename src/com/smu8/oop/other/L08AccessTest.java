package com.smu8.oop.other;
//com.smu8.oop.L07AccessModifier : 다른 패키지의 클래스를 호출

import com.smu8.oop.L07AccessModifier;

class ProtectedTest extends L07AccessModifier{
    public ProtectedTest(){
        L07AccessModifier m=new L07AccessModifier();
        m.a=111;
        //m.b=222;
        //m.c=333;
        //m.d=444;
        //객체를 만들지 않아도 상속받은 상속받은 부모가 객체로 존재
        //이때 부모객체를 super 로 참조
        super.a=1111;
        //super.b=2222;
        super.c=3333; //protected
        //super.d=4444;
    }
}
public class L08AccessTest {
    public static void main(String[] args) {
        L07AccessModifier m=new L07AccessModifier();
        m.a=11; //public : 어디서든
       // m.b=22; //default : 다른 패키지라 오류
       // m.c=33; //protected : 상속관계가 아니라서
       // m.d=44; //private : 클래스 내부에서만 가능
    }
}

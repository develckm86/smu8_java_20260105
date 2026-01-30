package com.smu8.oop;
interface Payable{
    void pay();
}
class CardPay implements Payable{
    @Override
    public void pay() {
        System.out.println("카드결제");
    }
}
public class L22AnonymousClass {
    /*new Payable(){  이하 코드를 컴파일러가 컴파일할때 자동완성하는 익명클래스
    class 1 implements Payable{
        @Override
        public void pay() {
            System.out.println("계좌이체를 진행합니다.");
        }
    }*/
    public static void main(String[] args) {
        //Payable payable=new Payable();
        Payable cardPay=new CardPay();
        cardPay.pay();
        //클래스 만들고 인터페이스를 구현하고 객체를 생성하는 것이 귀찮다!!!
        //인스턴스생성 : new 생성자();
        //=>익명클래스 : new 생성자(){};
        Payable bankPay=new Payable(){
            @Override
            public void pay() {
                System.out.println("계좌이체를 진행합니다.");
            }
        };
        bankPay.pay();
    }
}

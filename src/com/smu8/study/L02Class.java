package com.smu8.study;
class ClassTest{
    //java 문서에 작성된 다른 클래스는 컴파일시 .class로 분리
    //같은 패키지에 같은 이름의 class가 2개 존재할 수 없기 때문에 같은 이름의 자바문서(.java)를 만들 수 없다.
}
//public : 공유, 오픈된, 발간
//public class : 다른 사람이 공유 가능한 리소스, java 문서의 주인
public class L02Class {
    /*여러줄 주석 (실행되지 않는 코드)
    {} : 영역 Scope
    {} : 제일 밖에 있는 영역 root
    {{}} : 자식
    {{}{}} : 형제
    * */
    public static void main(String[] args) {
        System.out.println("안녕!");//무조건 코드가 종료되는 시점에 ';' 세미콜론을 사용함
        //System.out.println("안녕!")
        /* 자바의 기본 규칙
        * 1. 모든 코드는 class 내부에 존재 (class가 root다)
        * 2. 실행되는 코든 함수 내부에 존재
        * 3. 실행되는 코드의 종료 시점에 ; 세미콜론을 작성
        * 4. {} 영역은 꼭 열고 닫아야한다.
        * 5. 빨간줄은 컴파일오류 (실행불가)
        * */
    }
}
//System.out.println("안녕");  //실행은 무조건 함수 내부에서 작성
package com.smu8.oop;

class Student{
    int id;
    int birth;
    String name;
    String email;
    //this
    //toString() : 클래스를 만들면 자동으로 존재, 객체를 설명
    //대부분 개발툴에서 toString 자동완성을 지원

    public Student(){} //기본생성자 new Student()

    public Student(int id,int birth,String name){
        this.id=id;
        this.birth=birth;
        this.name=name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", birth=" + birth +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

public class L06Constructor {
    public static void main(String[] args) {
        //학생을 생성할때 학생은 꼭 이름과 나이와 학번이 있어야한다.
        Student s=new Student();
        s.name="철수";
        s.birth=2000;
        s.id=1234;
        System.out.println(s); //자동으로 toString ()
        //객체 성멸의 기본값 : 타입+저장된 메모리 위치  => 필드로 바꿀수 있다.
        //toString 기본값: com.smu8.oop.Student@5f184fc6
        //toString 변경 : Student{id=0, birth=0, name='null', email='null'}
        System.out.println(s.toString());

        Student s2=new Student(1244,2001,"영희");
        System.out.println(s2);
        //1. 모든 필드를 초기화 하는 생성자를 만들어보세요!! (**)
        //2. 모든 필드를 초기화 하는 생성자에서 id,birth,name을 초기화 하는 생성자를 이용하세요!
    }
}

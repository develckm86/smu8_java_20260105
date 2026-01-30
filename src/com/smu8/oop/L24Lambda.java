package com.smu8.oop;

import java.awt.event.ActionListener;
import java.util.function.Function;

@FunctionalInterface
interface LambdaTest{
    void a();
    static void b(){}; //필드와 관련 없으니 가능
}
@FunctionalInterface
interface ParamTest{
    int sum(int a, int b);
}
public class L24Lambda {
    public static void main(String[] args) {
        LambdaTest l=new LambdaTest() {
            @Override
            public void a() {

            }
        };
        LambdaTest l2=()->{};
        //ParamTest p=()->{}; ParamTest.sum(a,b) override
        ParamTest p=(a,b)->{return a+b;};
        System.out.println(p.sum(10,30));
        ParamTest p2=(a,b)->a+b;

        ActionListener a=(e)->{};
        Runnable run=()->{};
    }
}

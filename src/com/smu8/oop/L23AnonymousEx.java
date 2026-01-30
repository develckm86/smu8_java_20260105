package com.smu8.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//public action(){} //자바는 객체지향 언어기 때문에 함수 혼자 존재할수 없다.
//js=> 함수형언어 : 함수혼자 존재가능(함수 자체가 타입)
//class BtnHandler implements ActionListener{
//    @Override
//    public void actionPerformed(ActionEvent e) { // 버튼을 누르면 실행되는 기능
//
//    }
//}

class HelloFrame extends JFrame {
    private JButton btn;
    private JTextArea ta;
    class BtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String text=ta.getText();
            text+="안녕\n";
            ta.setText(text);
        }
    }
    public HelloFrame(){
        super("안녕 윈도우");
        btn=new JButton("say btn");
        //btn.addActionListener(new BtnHandler());
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setText("잘가!");
            }
        });
        ActionListener btnAction=(e)->{};

        btn.addActionListener(btnAction);

        super.add(btn, BorderLayout.NORTH);
        ta=new JTextArea();
        super.add(ta,BorderLayout.CENTER);
        //X 버튼을 누르면 어떻게 할건가??ㅌ
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//DISPOSE_ON_CLOSE 상태를 나타내는 상수
        super.setBounds(750,250,500,500);
        super.setVisible(true);
    }
}
public class L23AnonymousEx {
    public static void main(String[] args) {
        new HelloFrame();
    }
}

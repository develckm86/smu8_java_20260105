package com.smu8.oop;

import javax.swing.*;
import java.awt.*;

class CounterApp extends JFrame{
    private JLabel label;
    private JButton button;
    int count=0;
    public CounterApp(){
        super("카운터");
        //int count=0;
        // 지역변수 카운터를 람다식이나 익명클래스에서 접근하면 캡처가 되면서 상수로 변경
        label=new JLabel();
        Font font=new Font("Serif",Font.BOLD,30);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(font);
        label.setText(count+"");
        button=new JButton("+");
        button.addActionListener((e)->{
            label.setText( (++count)+"");
        });


        this.add(label);
        this.add(button, BorderLayout.SOUTH);
        this.setBounds(100,100,300,300);
        this.setVisible(true);
    }
}
public class L26CaptureEx {
    public static void main(String[] args) {
        CounterApp counterApp=new CounterApp();
    }
}

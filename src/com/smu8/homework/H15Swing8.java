package com.smu8.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class H15Swing8 extends JFrame {
    private JButton btn;
    private JLabel label;
    private JTextField tf;
    class BtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) { //콜백함수
            String numStr=tf.getText();
            try {
                double num=Double.parseDouble(numStr);
                label.setText(Double.toString(num*2));
            } catch (NumberFormatException ex) {
                label.setText("수만 입력가능!");
            }

        }
    }

    public H15Swing8(){
        super("swing 문제8");
        btn=new JButton("더블");
        btn.addActionListener(new BtnHandler());

        label=new JLabel("더블인 수",SwingConstants.CENTER);
        tf=new JTextField("0",5);
        JPanel p=new JPanel();
        p.setBackground(new Color(100, 192, 89, 105)); //배경색
        p.add(tf);
        p.add(btn);
        this.add(label);
        this.add(p,BorderLayout.SOUTH);
        this.setBounds(0,0,200,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new H15Swing8();
    }
}

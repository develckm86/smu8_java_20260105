package com.smu8.javautil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ShapeRect{
    int x=0;
    int y=0;
    int width=50;
    int height=50;
    //int r,g,b;
    Color color=Color.blue;
    public static Color createRandomColor(){
        Random random=new Random();
        int r=random.nextInt(256); //0~255
        int g=random.nextInt(256); //0~255
        int b=random.nextInt(256); //0~255
        Color color=new Color(r,g,b);
        return color;
    }
    //오버로드 : 함수(생성자)의 이름이 같은데 매개변수가 다른것
    public ShapeRect(int x, int y, int size){
        this(x,y,size,size,createRandomColor());
    }
    public ShapeRect(int x,int y,int size,Color color){
        this(x,y,size,size,color);
    }
    public ShapeRect(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public void draw( Graphics g){
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }
}

//복수의 도형을 그리고 움직이는 그래픽 예제
//1JFrame+JPanel(canvas)
public class L54MultiCanvas extends JFrame {
    class MyPanel extends JPanel{
        List<ShapeRect> shapeRectList=new ArrayList<>();
        public MyPanel(){
            this.setBackground(new Color(100,200,100));
            ShapeRect rect=new ShapeRect(20,20,100,70,Color.PINK);
            ShapeRect rect1=new ShapeRect(150,150,120,Color.MAGENTA);
            ShapeRect rect2=new ShapeRect(200,250,110);
            shapeRectList.add(rect);
            shapeRectList.add(rect1);
            shapeRectList.add(rect2);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(ShapeRect shapeRect: shapeRectList){
                shapeRect.draw(g);
            }
//            g.setColor(new Color(194, 66, 66));
//            g.fillRect(0,0,50,50);
//            g.setColor(Color.GRAY); //255,255,255
//            g.fill3DRect(100,100,100,100,false); //사각현 버튼처럼 눌리거나 나와 있어보이게 출력
//            g.fill3DRect(210,100,100,100,true); //사각현 버튼처럼 눌리거나 나와 있어보이게 출력
        }
    }
    public L54MultiCanvas(){
        super("멀티 도형 캔버스");
        MyPanel canvas=new MyPanel();
        this.setContentPane(canvas);
        this.setBounds(-500,0,500,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            JFrame frame=new L54MultiCanvas();
        });
    }
}

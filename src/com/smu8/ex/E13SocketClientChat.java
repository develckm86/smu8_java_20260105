package com.smu8.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class E13SocketClientChat {
    public void serverConnect(String serverIp,int port){
        try(Socket client=new Socket(serverIp,port)){
            System.out.println("접속성공");
            Thread sendMsgThread=new Thread(()->sendMsg(client));
            Thread receiveMsgThread=new Thread(()->receiveMsg(client));
            sendMsgThread.start();
            receiveMsgThread.start();
            sendMsgThread.join();
            receiveMsgThread.join();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void receiveMsg(Socket client){
        try (Scanner scanner=new Scanner(client.getInputStream())){
            while (true){
                String msg=scanner.nextLine(); // 서버가 보낸 채팅 메세지 내가 보낸것 포함
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Socket client){
        Scanner scanner=new Scanner(System.in);
        try (PrintWriter printWriter=new PrintWriter(client.getOutputStream())){
           while (true) {
               String msg=scanner.nextLine(); //채팅입력
               printWriter.println(msg); //서버에 채팅내역 보내기
               printWriter.flush(); //메세지 보낸 버퍼 비우기
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String serverIp="192.168.0.116";
        int port=5555;
        E13SocketClientChat socketClientChat=new E13SocketClientChat();
        socketClientChat.serverConnect(serverIp,port);
    }
}

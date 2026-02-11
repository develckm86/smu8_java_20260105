package com.smu8.ex;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E12SocketServerChat {
    List<Socket> clients =new ArrayList<>();//접속한 유저 관리

    public void startServer() {
        int port=5555;
        try(ServerSocket server=new ServerSocket(port);){
            String ip= InetAddress.getLocalHost().getHostAddress();
            System.out.println("서버 주소 :"+ip+":"+port);
            while (true){ //접속하는 모든 유저 관리
                Socket socket=server.accept(); //유저 접속시 생시는 객체
                clients.add(socket);
                System.out.println("접속한 유저 "+socket.getInetAddress());
                Thread receveThread=new Thread(()-> receiveMsg(socket));
                receveThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveMsg(Socket client) {
        try(Scanner scanner=new Scanner(client.getInputStream())){
            while (true){
                String msg=scanner.nextLine();
                String userIp=client.getInetAddress().getHostAddress();

                List<Socket> closeClients=new ArrayList<>(); //닫힌 소켓 모음
                clients.stream()
                        .forEach((c)->{
                    try {//메세지 보내면서 auto close를 하면 소켓 연결이 끊어짐 주의!!
                        PrintWriter printWriter=new PrintWriter(c.getOutputStream());
                        printWriter.println(userIp+" : "+msg);
                        printWriter.flush();
                    } catch (IOException e) {//접속한 클라이언크가 문제가 있음
                        closeClients.add(c); //문제가 있는 클라이언트 모음
                        e.printStackTrace();
                    }
                });
                clients.removeAll(closeClients);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        E12SocketServerChat serverChat=new E12SocketServerChat();
        serverChat.startServer();
    }
}

package com.smu8.ex;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 접속자 1명을 나타내는 클래스
 * - 소켓, 입력/출력 스트림, 닉네임을 보관한다.
 * - AutoCloseable을 구현하여 try-with-resources로 소켓을 정리할 수 있게 한다.
 */
class Client implements AutoCloseable {
    final Socket socket;
    final BufferedReader in;
    final BufferedWriter out;

    // 여러 스레드에서 읽힐 수 있는 값이므로 가시성을 위해 volatile 사용
    volatile String nickname="익명";

    Client(Socket socket) throws IOException {
        this.socket = socket;

        // 서버 <- 클라이언트 : 한 줄 단위로 읽기
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
        );

        // 서버 -> 클라이언트 : 한 줄 단위로 쓰기(BufferedWriter는 flush가 필요)
        this.out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
        );
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class E14ChatServer {

    // 접속자 목록
    // Collections.synchronizedList는 add/remove는 내부적으로 동기화되지만,
    // "순회"는 synchronized 블록으로 감싸야 안전하다.
    private final List<Client> clients = Collections.synchronizedList(new ArrayList<>());

    // 클라이언트가 접속자 목록을 갱신할 때 사용할 프로토콜 라인
    // 예) @USERS user1,user2,user3
    private static final String USERS_PREFIX = "@USERS ";

    public static void main(String[] args) throws Exception {
        new E14ChatServer().start(6000);
    }

    public void start(int port) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[SERVER] listening on " + port);

            while (true) {
                // 클라이언트 접속 대기(블로킹)
                Socket socket = serverSocket.accept();

                // 접속자 객체 생성 및 목록에 추가
                Client client = new Client(socket);
                clients.add(client);

                // 접속자마다 전용 스레드를 만들어 메시지 수신/처리를 담당
                Thread t = new Thread(() -> handle(client));
                t.setDaemon(true);
                t.start();
            }
        }
    }

    private void handle(Client client) {
        try (client) {
            // 첫 접속 시 클라이언트가 보내는 첫 줄 = 닉네임
            String nickname = client.in.readLine();
            if (nickname == null || nickname.isBlank()) nickname = "익명";
            client.nickname = nickname.trim();

            // 입장 메시지를 전체에게 알림
            broadcast("[SYSTEM] " + client.nickname + " 님이 입장했습니다.");

            // 접속자 목록을 모든 클라이언트에 전송(@USERS 프로토콜)
            broadcastUsers();

            String line;
            while ((line = client.in.readLine()) != null) {
                // 문자열 뒤 공백 제거(앞 공백은 유지: 채팅에서 의도적으로 앞 공백을 쓸 수도 있음)
                line = line.stripTrailing();

                // 빈 줄 무시
                if (line.isBlank()) continue;

                // 종료 명령
                if (line.equalsIgnoreCase("/quit")) break;

                // 일반 채팅 브로드캐스트
                broadcast(client.nickname + " : " + line);
            }
        } catch (IOException e) {
            // 연결 끊김, 네트워크 오류 등
            e.printStackTrace();
        } finally {
            // 접속자 목록에서 제거
            clients.remove(client);

            // 퇴장 알림
            if (client.nickname != null) {
                broadcast("[SYSTEM] " + client.nickname + " 님이 퇴장했습니다.");
            }

            // 접속자 목록 갱신 전송
            broadcastUsers();
        }
    }

    /**
     * 전체 접속자에게 메시지 1줄을 전송한다.
     * - BufferedWriter는 newLine() + flush()가 필요하다.
     * - 순회 중 오류가 나면 해당 클라이언트를 제거한다.
     *
     * 주의: clients가 synchronizedList이므로 "순회"는 synchronized(clients)로 감싸야 안전하다.
     */
    private void broadcast(String msg) {
        System.out.println(msg);

        synchronized (clients) {
            Iterator<Client> it = clients.iterator();

            while (it.hasNext()) {
                Client c = it.next();
                try {
                    c.out.write(msg);
                    c.out.newLine(); // 클라이언트가 readLine()으로 받으므로 줄 종료 필요
                    c.out.flush();   // 즉시 전송(채팅은 지연 없이 받는 게 중요)
                } catch (IOException e) {
                    // 전송 실패 = 접속이 끊긴 것으로 보고 제거
                    it.remove();
                    c.close();
                }
            }
        }
    }

    /**
     * 현재 접속자 닉네임 목록을 @USERS 형태로 모든 클라이언트에 전송한다.
     * 클라이언트는 이 라인을 받으면 사용자 목록 UI를 갱신한다.
     */
    private void broadcastUsers() {
        String usersLine;
        synchronized (clients) {
            usersLine = USERS_PREFIX + clients.stream()
                    .map(c -> c.nickname) //이름만
                    //.filter(Objects::nonNull) //null처리
                    //.map(String::trim) //공백처리
                    //.filter(s -> !s.isBlank()) //공백닉네임제거
                    //.distinct() //중복제거(현재는 이름이 중복될 수 있음)
                    .sorted()
                    .collect(java.util.stream.Collectors.joining(","));
        }
        broadcast(usersLine);
    }

}
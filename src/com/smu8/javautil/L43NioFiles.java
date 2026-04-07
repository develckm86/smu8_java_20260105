package com.smu8.javautil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class L43NioFiles {
    public static void main(String[] args) throws IOException {
        Path path=Path.of("nio-sample.txt");
        String message="NIO는 Path와 Files 유틸리티로 파일을 다룹니다.";

        Files.writeString(path, message+System.lineSeparator(), StandardCharsets.UTF_8);

        String text=Files.readString(path, StandardCharsets.UTF_8);
        List<String> lines=Files.readAllLines(path, StandardCharsets.UTF_8);

        System.out.println("파일 경로 : "+path.toAbsolutePath());
        System.out.println("전체 텍스트 : "+text.strip());
        System.out.println("줄 수 : "+lines.size());

        Files.deleteIfExists(path);
    }
}

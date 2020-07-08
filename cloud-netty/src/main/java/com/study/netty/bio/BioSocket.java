package com.study.netty.bio;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class BioSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(9091);
        Socket client = server.accept();
        System.out.println("1:"+System.currentTimeMillis());
        InputStream inputStream = client.getInputStream();
//        System.out.println("2:"+System.currentTimeMillis());
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        System.out.println("3:"+System.currentTimeMillis());
//        BufferedReader reader = new BufferedReader(inputStreamReader);
//        System.out.println("4:"+System.currentTimeMillis());
//        System.out.println(reader.readLine());
//        System.out.println("5:"+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            System.out.println("read "+i);
            int resultLength =inputStream.available();
            if (resultLength > 0){
                System.out.println("resultLength:"+resultLength);
                byte [] result = new byte[resultLength];
                inputStream.read(result);
//                byte[] body =new byte[resultLength-20] ;
//                System.arraycopy(result, 20, body, 0, body.length);
//                System.out.println(Arrays.toString(body));
                System.out.println(new String(result));
                break;
            }
            Thread.sleep(1000);

        }
    }
}

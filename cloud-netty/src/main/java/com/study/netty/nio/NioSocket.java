package com.study.netty.nio;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class NioSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<SocketChannel> clients = new LinkedList<>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            Thread.sleep(1000);
            SocketChannel client = serverSocketChannel.accept();
            if (client == null) {
            } else {
                client.configureBlocking(false);
                int port = client.socket().getPort();
                System.out.println("new client port:"+port);
                clients.add(client);
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(5);
            clients.forEach(c ->{
                try {
                    int num = c.read(buffer);
                    if (num > 0){
                        buffer.flip();
                        byte [] bytes = new byte[buffer.limit()];
                        buffer.get(bytes);
                        String result = new String(bytes);
                        buffer.clear();
                        System.out.println("client port:"+c.socket().getPort()+" send :"+result);
                    }
                } catch (IOException e) {
                    try {
                        log.info("some error has being");
                        c.close();
                        clients.remove(c);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });
        }
    }

}

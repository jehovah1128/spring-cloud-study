package com.study.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioSingleThread {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    int port = 9090;

    public void initServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    public void start() throws IOException {
        initServer();
        System.out.println("sever start port:"+serverSocketChannel.socket().getLocalPort());
        while (true){
            while (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    selectionKeys.remove(key);
                    if (key.isAcceptable()){
                        acceptHandler(key);
                    }else if (key.isReadable()){
                        readHandler(key);
                    }
                }
            }
        }

    }

    public void readHandler(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        int read = 0;
        while (true){
            read =client.read(buffer);
            if (read >0){
                buffer.flip();
                while (buffer.hasRemaining()){
                    byte [] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    String result = new String(bytes);
                    System.out.println("client port:"+client.socket().getPort()+" send :"+result);
//                    buffer.clear();
//                    buffer.put("success\n".getBytes());
//                    buffer.flip();
                    buffer.position(0);
                    client.write(buffer);
                }
                buffer.clear();
            }else if (read == 0){
                System.out.println("no message !!");
                break;
            }else{
                System.out.println("client has closed address:"+client.getRemoteAddress()+",port:"+client.socket().getPort());
                client.close();
                break;
            }
        }


    }

    public void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel client = serverChannel.accept();
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
        client.register(selector, SelectionKey.OP_READ,buffer);
        System.out.println("new client address:"+client.getRemoteAddress()+",port:"+client.socket().getPort());
    }

    public static void main(String[] args) throws IOException {
        NioSingleThread selector = new NioSingleThread();
        selector.start();
    }
}

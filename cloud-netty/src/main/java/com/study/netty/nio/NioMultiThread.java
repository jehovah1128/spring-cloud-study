package com.study.netty.nio;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class NioMultiThread {
    private ServerSocketChannel serverSocketChannel;
    private Selector selectorBoss;
    private Selector selectorWorker1;
    private Selector selectorWorker2;
    int port = 9090;

    public void initServer() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        selectorBoss = Selector.open();
        selectorWorker1 = Selector.open();
        selectorWorker2 = Selector.open();
        serverSocketChannel.register(selectorBoss, SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NioMultiThread nioMultiThread = new NioMultiThread();
        nioMultiThread.initServer();
        NioThread threadBoss = new NioThread(nioMultiThread.selectorBoss,2);
        NioThread threadWorker1 = new NioThread(nioMultiThread.selectorWorker1);
        NioThread threadWorker2 = new NioThread(nioMultiThread.selectorWorker2);

        threadBoss.start();
        Thread.sleep(1000);
        System.out.println("boss start");
        threadWorker1.start();
        threadWorker2.start();
    }
    public static class NioThread extends Thread{
        private Selector selector;
        static int workerNum = 0;
        int id = 0;
        static BlockingQueue<SocketChannel>[] queues;
        static AtomicInteger idx = new AtomicInteger();

        NioThread(Selector selector,int workerNum){
            this.selector = selector;
            this.workerNum = workerNum;
            this.id = workerNum;
            queues = new LinkedBlockingQueue[this.workerNum];
            for (int i = 0; i < workerNum; i++) {
                queues[i] = new LinkedBlockingQueue<>();
            }
        }
        NioThread(Selector selector){
            this.selector = selector;
            id = idx.getAndIncrement() % workerNum;

        }
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                while (selector.select(10) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        selectionKeys.remove(key);
                        if (key.isAcceptable()) {
                            acceptHandler(key);
                        } else if (key.isReadable()) {
                            readHandler(key);
                        }
                    }
                }
                if (id<workerNum&&!queues[id].isEmpty()){
                    ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                    SocketChannel client = queues[id].take();
                    client.register(selector, SelectionKey.OP_READ,buffer);
                    System.out.println("new client address:"+client.getRemoteAddress()+",port:"+client.socket().getPort()+",worker:"+id+"thread id:"+Thread.currentThread().getId());
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
                    break;
                }else{
                    System.out.println("client has closed address:"+client.getRemoteAddress()+",port:"+client.socket().getPort());
                    client.close();
                    break;
                }
            }


        }

        public void acceptHandler(SelectionKey key) throws IOException {
            System.out.println("have new client connect on thread id:"+Thread.currentThread().getId());
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel client = serverChannel.accept();
            client.configureBlocking(false);
            int num = idx.getAndIncrement() % workerNum;
            queues[num].add(client);

        }
    }
}

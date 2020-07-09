package com.study.nettytest;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NoiFileChannelWriteTest {
    public static void main(String[] args) throws Exception {
        String a = "hello netty! \n";
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\study\\test.txt",true);
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(a.getBytes());
        buffer.flip();
        channel.write(buffer);
        fileOutputStream.close();
    }
}

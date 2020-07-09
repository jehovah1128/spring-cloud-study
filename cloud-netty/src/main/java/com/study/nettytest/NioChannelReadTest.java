package com.study.nettytest;


import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioChannelReadTest {
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\study\\test.txt");
        FileInputStream is = new FileInputStream(file);
        FileChannel channel = is.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        channel.read(buffer);
        System.out.println(new String(buffer.array()));
        is.close();
    }
}

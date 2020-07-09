package com.study.nettytest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NidFileChannelRWTest {
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\study\\test.txt");
        FileInputStream is = new FileInputStream(file);
        FileChannel channel = is.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\study\\test2.txt",true);
        FileChannel writeChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(8);
        while (true){
            buffer.clear();
            int read = channel.read(buffer);
            if (read == -1)
                break;
            buffer.flip();
            writeChannel.write(buffer);
        }
        is.close();
        fileOutputStream.close();

    }
}

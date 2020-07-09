package com.study.nettytest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NioFileChannelCopyFileTest {

    public static void main(String[] args) throws Exception {
        File file = new File("d:\\study\\test.txt");
        FileInputStream is = new FileInputStream(file);
        FileChannel channel = is.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\study\\test4.txt",true);
        FileChannel writeChannel = fileOutputStream.getChannel();
//        writeChannel.transferFrom(channel, 0, channel.size());
        channel.transferTo(0, channel.size(), writeChannel);
        is.close();
        fileOutputStream.close();
    }
}

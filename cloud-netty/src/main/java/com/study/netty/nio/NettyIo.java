package com.study.netty.nio;

import io.netty.channel.nio.NioEventLoopGroup;

public class NettyIo {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);

    }
}

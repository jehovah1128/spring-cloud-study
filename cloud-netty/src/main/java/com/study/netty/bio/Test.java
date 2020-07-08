package com.study.netty.bio;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] a = new byte[]{103, 107, 112, 46, 35, 0, 0, 0, 72, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, -26, -120, -111, -24, -90, -127, -27, -123, -77, -26, -100, -70, -27, -107, -90};
        byte[] b = new byte[a.length-20];
        System.arraycopy(a, 20, b, 0, b.length);
        System.out.println(Arrays.toString(b));

    }
}

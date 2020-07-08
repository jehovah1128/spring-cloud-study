package com.study.netty.bio;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ByteUtil {
    public static int bytes2Int(byte[] bytes) {
        int result = 0;
        //将每个byte依次搬运到int相应的位置
        result = bytes[0] & 0xff;
        result = result << 8 | bytes[1] & 0xff;
        result = result << 8 | bytes[2] & 0xff;
        result = result << 8 | bytes[3] & 0xff;
        return result;
    }

    public static byte[] int2Bytes(int num) {
        byte[] bytes = new byte[4];
        //通过移位运算，截取低8位的方式，将int保存到byte数组
        bytes[0] = (byte)(num >>> 24);
        bytes[1] = (byte)(num >>> 16);
        bytes[2] = (byte)(num >>> 8);
        bytes[3] = (byte)num;
        return bytes;
    }

    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }


    public static void main(String[] args) {
        System.out.println(new String(new byte[]{20,0}));

    }

    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append((int)chars[i]).append(",");
            }
            else {
                sbu.append((int)chars[i]);
            }
        }
        return sbu.toString();
    }

    /**
     * Ascii转换为字符串
     * @param value
     * @return
     */
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
            sbu.append((char) Integer.parseInt(value));
        return sbu.toString();
    }
}

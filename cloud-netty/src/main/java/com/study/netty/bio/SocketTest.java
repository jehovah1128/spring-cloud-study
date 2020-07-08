package com.study.netty.bio;

import org.apache.tomcat.util.buf.MessageBytes;
import sun.security.util.Length;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class SocketTest {
    static final String HEAD_SIG = "gkp.";
    static final int MSG_HEADER_LEN = 20;
    static final String WUJI_S2C_CONFIG_VIDEO_PROP = "E";
    static final int PROTO_FORMAT = 3;
    static final String SUCCESS = "success";

    public static boolean senMessage(String host, int port, String message) throws IOException, InterruptedException {
        Socket socket = new Socket(host, port);
        OutputStream os = socket.getOutputStream();
        MessageBytes bytes = MessageBytes.create(HEAD_SIG, MSG_HEADER_LEN, WUJI_S2C_CONFIG_VIDEO_PROP, PROTO_FORMAT, message);
        os.write(bytes.getByte());
        InputStream is = socket.getInputStream();
        for (int i = 0; i < 10; i++) {
            System.out.println("read "+i);
            int resultLength =is.available();
            if (resultLength > 0){
                byte [] result = new byte[resultLength];
                is.read(result);
                byte[] body =new byte[resultLength-20] ;
                System.arraycopy(result, 20, body, 0, body.length);
                String res = new String(body);
                System.out.println(res);
                if (res.equals(SUCCESS)){
                    System.out.println(1);
                    return true;
                }else {
                    return false;
                }
            }
            Thread.sleep(1000);
        }
        return false;

    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        senMessage("192.168.58.1",8666,"123456");
        senMessage("121.201.33.126", 6032, "3b65450a468641f9b78685b185646383");
    }

    public static class MessageBytes {
        public static byte[] messageByte;
        int length = 0;
        static String charset = "UTF-8";

        public static MessageBytes create(String sig, int dataLen, String msgId, int protoFormat, String message) throws UnsupportedEncodingException {
            byte[] strArr = message.getBytes(charset);
            int totalLength = dataLen + strArr.length;
            MessageBytes messageBytes = new MessageBytes();
            messageBytes.init(totalLength);
            messageBytes.stringToByte(sig, 4);
            messageBytes.intToByte(totalLength, 4);
            messageBytes.stringToByte(msgId, 2);
            messageBytes.intToByte(protoFormat, 2);
            messageBytes.trandByte();
            messageBytes.stringToByte(strArr);
            return messageBytes;
        }

        void init(int dataLen) {
            messageByte = new byte[dataLen];
        }

        public void stringToByte(byte[] strArr) {
            for (int i = 0; i < strArr.length; i++) {
                messageByte[length] = strArr[i];
                length++;
            }
        }

        public void stringToByte(String message, int alen) {
            try {
                byte[] strArr = message.getBytes(charset);
                for (int i = 0; i < alen; i++) {
                    if (i < strArr.length) {
                        messageByte[length] = strArr[i];
                    } else {
                        messageByte[length] = 0;
                    }
                    length++;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public void intToByte(int message, int alen) {
            byte[] bytes = ByteUtil.int2Bytes(message);
            for (int i = 3; i > 4 - alen - 1; i--) {
                messageByte[length] = bytes[i];
                length++;
            }
        }

        public void trandByte() {
            for (int i = length; i < 20; i++) {
                messageByte[length] = 0;
                length++;
            }
        }

        public byte[] getByte() {
            return this.messageByte;
        }
    }
}

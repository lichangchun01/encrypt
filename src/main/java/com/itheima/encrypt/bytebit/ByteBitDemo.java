package com.itheima.encrypt.bytebit;

import java.io.UnsupportedEncodingException;

public class ByteBitDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String a="黑";
        byte[] bytes = a.getBytes("UTF-8");
        for (byte b : bytes) {
            int c= b;
            System.out.print(c +"    ");

            String s = Integer.toBinaryString(c);
            System.out.println(s);
        }
    }
}

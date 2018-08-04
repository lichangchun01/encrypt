package com.itheima.encrypt.digest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class DigestDemo {
    public static void main(String[] args) throws Exception {
        // 4124bc0a9335c27f086f24ba207a4912
        // 4124bc0a9335c27f086f24ba207a4912
        //String input = "aa";
        //
        //String algorithm = "MD5";
    String md5 = getDigest("123", "MD5");
      System.out.println(md5);
        String md52 = getDigest("123 ", "MD5");
        System.out.println(md52);
        //String sha1 = getDigest(input, "SHA-1");
        //System.out.println(sha1);
        //String sha256 = getDigest(input, "SHA-256");
        //System.out.println(sha256);
        //String sha512 = getDigest(input, "SHA-512");
        //System.out.println(sha512);

        //String sha1 = getDigestFile("11.zip", "SHA-1");
        //System.out.println(sha1);
        //String sha512 = getDigestFile("11.zip", "SHA-512");
        //System.out.println(sha512);

        // 35876dd986890ddb5eb501f66a3f7c7d3fa6caa5   111
        // 35876dd986890ddb5eb501f66a3f7c7d3fa6caa5
        // dc3743e7bb409de25d1df26656cad1e15c206af17751f0d8ae5898c1325efac0c69918228649696f32126e4e32c99a73b422ef96b61cef75be384a4d0aef9c9f 111
        // dc3743e7bb409de25d1df26656cad1e15c206af17751f0d8ae5898c1325efac0c69918228649696f32126e4e32c99a73b422ef96b61cef75be384a4d0aef9c9f
    }

    /**
     * 获取消息摘要
     *
     * @param input     : 原文
     * @param algorithm : 算法
     * @return : 消息摘要
     * @throws Exception
     */
    public static String getDigest(String input, String algorithm) throws Exception {
        // 获取MessageDigest对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 生成消息摘要
        byte[] digest = messageDigest.digest(input.getBytes());

        System.out.println("密文的字节长度:" + digest.length);
        return toHex(digest);

    }

    public static String getDigestFile(String filePath, String algorithm) throws Exception {

        FileInputStream fis = new FileInputStream(filePath);
        int len;
        byte[] buffer = new byte[1024];

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((len = fis.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }

        // 获取MessageDigest对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 生成消息摘要
        byte[] digest = messageDigest.digest(baos.toByteArray());

        System.out.println("密文的字节长度:" + digest.length);
        return toHex(digest);

    }

    // 将字节数组转为16进制字符串
    public static String toHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            int i = b & 0xff;
            String s = Integer.toHexString(i);
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }

        System.out.println("16进制数据的长度:" + sb.toString().getBytes().length);
        return sb.toString();
    }
}

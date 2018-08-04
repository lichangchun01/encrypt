package com.itheima.encrypt.desaes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesDemo {

    public static void main(String[] args) throws Exception {
        //  原文
        String input = "黑马黑马黑1";
        // 密码
        String key = "1234567812345678";
        // 指定获取Cipher的算法
        //  String transformation = "AES";
        //String transformation = "DES/ECB/PKCS5Padding"; // 9PQXVUIhaaQ=
        // CBC模式,必须指定初始向量,初始向量中密钥的长度必须是16个字节
      //  String transformation = "AES/CBC/PKCS5Padding"; // 9PQXVUIhaaQ=
        // NoPadding模式,原文的长度必须是16个字节的整倍数
        String transformation = "AES/CBC/NoPadding"; // 9PQXVUIhaaQ=
        // 指定获取密钥的算法
        String algorithm = "AES";
        String encryptDES = encryptAES(input, key, transformation, algorithm);
        System.out.println("加密:" + encryptDES);
        String s = dncryptAES(encryptDES, key, transformation, algorithm);
        System.out.println("解密:" + s);

    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(AES,密钥的长度必须是16个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception
     */
    private static String encryptAES(String input, String key, String transformation, String algorithm) throws Exception {
        // 1,获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定密钥规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 2.指定规则(加密)和密钥
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, sks, iv);
        // 3. 加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        //String s = Base64.getEncoder().encodeToString(bytes);
        //System.out.println(s);
        // 对数据进行Base64编码
        String encode = Base64.encode(bytes);

        return encode;
    }

    /**
     * 使用DES解密
     *
     * @param input          : 密文
     * @param key            : 密钥
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @throws Exception
     * @return: 原文
     */
    private static String dncryptAES(String input, String key, String transformation, String algorithm) throws Exception {
        // 1,获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定密钥规则
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 2.指定规则(解密)和密钥
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, sks, iv);
        // 3. 解密
        byte[] bytes = cipher.doFinal(Base64.decode(input));

        return new String(bytes);
    }
}

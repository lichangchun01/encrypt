package com.itheima.encrypt.signature;

import com.itheima.encrypt.rsa.RSAdemo;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SignatureDemo {
    public static void main(String[] args) throws Exception {
        String input = "黑马";
        String input2 = "黑马   ";
        String algorithm = "RSA";
        PrivateKey privateKey = RSAdemo.getPrivateKey("a.pri", algorithm);
        PublicKey publicKey = RSAdemo.getPublicKey("a.pub", algorithm);

        String signatureAlgorithm = "SHA256withRSA";
        String signatured = getSignature(input, privateKey, signatureAlgorithm);

        Signature signature = Signature.getInstance(signatureAlgorithm);
        // 初始化验证,指定公钥
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input2.getBytes());
        // 验证签名
        boolean verify = signature.verify(Base64.decode(signatured));

        System.out.println(verify);

    }

    public static String getSignature(String input, PrivateKey privateKey, String signatureAlgorithm) throws Exception {
        Signature signature = Signature.getInstance(signatureAlgorithm);
        // 初始化签名,指定私钥
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 签名
        byte[] sign = signature.sign();

        return Base64.encode(sign);
    }

    public static boolean verifySignature(String input, PublicKey publicKey, String signatureAlgorithm, String signatured) throws Exception {
        Signature signature = Signature.getInstance(signatureAlgorithm);
        // 初始化验证,指定公钥
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 验证签名
        return signature.verify(Base64.decode(signatured));

    }
}

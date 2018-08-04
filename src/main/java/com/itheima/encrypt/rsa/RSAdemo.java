package com.itheima.encrypt.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAdemo {
    public static void main(String[] args) throws Exception {
        String input = " 修改maven根目录下的conf文件夹中的setting.xml文件，内容如下：\n" +
                "\n" +
                "<mirrors>\n" +
                "    <mirror>\n" +
                "      <id>alimaven</id>\n" +
                "      <name>aliyun maven</name>\n" +
                "      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>\n" +
                "      <mirrorOf>central</mirrorOf>        \n" +
                "    </mirror>\n" +
                "  </mirrors>\n" +
                "\n" +
                "之后就能享受如飞的maven下载速度。 ";
        String algorithm = "RSA";

        PrivateKey privateKey = getPrivateKey("a.pri", algorithm);
        PublicKey publicKey = getPublicKey("a.pub", algorithm);

        String s = encryptRSA(algorithm, privateKey, input, 245);
        String s1 = decryptRSA(algorithm, publicKey, s,256);
        System.out.println(s1);

    }

    public static PrivateKey getPrivateKey(String priPath, String algorithm) throws Exception {
        String privateKeyString = FileUtils.readFileToString(new File("a.pri"), Charset.defaultCharset());

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        return keyFactory.generatePrivate(spec);
    }

    public static PublicKey getPublicKey(String priPath, String algorithm) throws Exception {
        String publicKeyString = FileUtils.readFileToString(new File(priPath), Charset.defaultCharset());

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decode(publicKeyString));

        return keyFactory.generatePublic(spec);
    }

    public static void generateKeys(String algorithm, String priPath, String pubPath) throws Exception {
        // 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公私钥
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        // 获取公私钥的字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 对公私钥进行Base64的编码
        String privateKeyString = Base64.encode(privateKeyEncoded);
        String publicKeyString = Base64.encode(publicKeyEncoded);

        FileUtils.writeStringToFile(new File(priPath), privateKeyString, Charset.defaultCharset());
        FileUtils.writeStringToFile(new File(pubPath), publicKeyString, Charset.defaultCharset());
    }

    public static String encryptRSA(String algorithm, Key key, String input, int maxEncryptSize) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] data = input.getBytes();
        int total = data.length;
        int offset = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes;
        while (total - offset > 0) {

            if (total - offset >= maxEncryptSize) {
                bytes = cipher.doFinal(data, offset, maxEncryptSize);
                offset += maxEncryptSize;
            } else {
                bytes = cipher.doFinal(data, offset, total - offset);
                offset = total;
            }

            baos.write(bytes);
        }

        return Base64.encode(baos.toByteArray());
    }

    public static String decryptRSA(String algorithm, Key key, String input, int maxEncryptSize) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] data = Base64.decode(input);
        int total = data.length;
        int offset = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes;
        while (total - offset > 0) {

            if (total - offset >= maxEncryptSize) {
                bytes = cipher.doFinal(data, offset, maxEncryptSize);
                offset += maxEncryptSize;
            } else {
                bytes = cipher.doFinal(data, offset, total - offset);
                offset = total;
            }

            baos.write(bytes);
        }

        return baos.toString();
    }

}

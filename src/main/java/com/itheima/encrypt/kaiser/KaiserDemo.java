package com.itheima.encrypt.kaiser;

public class KaiserDemo {
    public static void main(String[] args) {
        String input = "Hello world";
        int key = 3;

        String encryptKaiser = encryptKaiser(input, key);
        System.out.println("加密:"+encryptKaiser);
        String decryptKaiser = decryptKaiser(encryptKaiser, key);
        System.out.println("解密:"+decryptKaiser);

    }

    public static String encryptKaiser(String input, int key) {
        StringBuilder sb = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char c : chars) {
            int asciiCode = c;
            asciiCode = asciiCode + key;
            char newChar = (char) asciiCode;
           sb.append(newChar);
        }

      return sb.toString();
    }

    public static String decryptKaiser(String input, int key) {
        StringBuilder sb = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char c : chars) {
            int asciiCode = c;
            asciiCode = asciiCode - key;
            char newChar = (char) asciiCode;
            sb.append(newChar);
        }

        return sb.toString();
    }
}

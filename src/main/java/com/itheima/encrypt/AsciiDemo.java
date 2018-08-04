package com.itheima.encrypt;

public class AsciiDemo {
    public static void main(String[] args) {

        //char a = 'A';
        //int b = a;
        //System.out.println(b);

        String a="AaZ";
        char[] chars = a.toCharArray();
        for (char aChar : chars) {
            int asciiCode=aChar;
            System.out.println(asciiCode);
        }

    }
}

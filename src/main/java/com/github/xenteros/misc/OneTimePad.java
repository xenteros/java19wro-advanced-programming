package com.github.xenteros.misc;

import java.util.Base64;

class OneTimePad {

    private String key;
    private int keyLettersUsed = 0;

    public OneTimePad(String key) {
        this.key = key;
    }

    public String encryptDecrypt(String message) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char next = (char) (message.charAt(i) ^ key.charAt(i + keyLettersUsed));
            result.append(next);
        }
        keyLettersUsed += result.length();
        return result.toString();
    }

    public static String toBase64(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    public static String fromBase64(String s) {
        return new String(Base64.getDecoder().decode(s));
    }


    public static void main(String[] args) {
        OneTimePad alice = new OneTimePad("abcdefghijklmnoprstuwxyz");
        OneTimePad bob = new OneTimePad("abcdefghijklmnoprstuwxyz");

        String encryptedHelloWorldInBase64 = toBase64(alice.encryptDecrypt("Hello World"));
        System.out.println("-----------------");
        System.out.println(encryptedHelloWorldInBase64);
        System.out.println("-----------------");
        System.out.println(bob.encryptDecrypt(fromBase64(encryptedHelloWorldInBase64)));
        System.out.println("-----------------");
    }
}

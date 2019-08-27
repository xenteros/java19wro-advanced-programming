package com.github.xenteros.misc;

import org.jasypt.util.text.BasicTextEncryptor;

class SimpleEncryptionWithJasypy {

    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("password");

        String secretMessage = "Hello World";

        String encrypted = textEncryptor.encrypt(secretMessage);

        System.out.println(encrypted);
        System.out.println(textEncryptor.decrypt(encrypted));


    }
}

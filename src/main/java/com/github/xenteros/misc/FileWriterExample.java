package com.github.xenteros.misc;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.util.Arrays;

class FileWriterExample {

    public static void main(String[] args) throws IOException {

//        List<PasswordEntry> passwordEntries = new ArrayList<>();
//        passwordEntries.add(new PasswordEntry("Facebook", "pass"));
//        passwordEntries.add(new PasswordEntry("Gmail", "password"));
//
//        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
//        textEncryptor.setPassword("super-secret");
//
//        FileWriter fileWriter = new FileWriter("passwords.csv");
//        for (PasswordEntry passwordEntry : passwordEntries) {
//            String encrypted = textEncryptor.encrypt(passwordEntry.toCsvString());
//            fileWriter.write(encrypted + "\n");
//        }
//        fileWriter.close();

        CSVParserBuilder parserBuilder = new CSVParserBuilder() // parser builder do parametrów
                .withEscapeChar('\\')
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('"')
                .withSeparator(';');

        CSVReaderBuilder readerBuilder = new CSVReaderBuilder(new DecipherFileReader("passwords.csv", "super-secret")).withCSVParser(parserBuilder.build());
        CSVReader reader = readerBuilder.build();
        for (String[] strings : reader.readAll()) {
            System.out.println(Arrays.toString(strings));
        }
    }
}

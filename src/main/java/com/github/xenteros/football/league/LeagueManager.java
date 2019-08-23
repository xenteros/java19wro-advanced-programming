package com.github.xenteros.football.league;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;


@Log4j
class LeagueManager {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

//        Team one = new Team(1, emptyList(), "druzyna");
//        Team two = new Team(2, emptyList(), "druzyna2");
//
//        FileManager.dumpTeamsToFile(Arrays.asList(one, two));
//        List<Team> teams = FileManager.readTeamsFromFile();
//        teams.forEach(System.out::println);

        char[] mycbuf = new char[100];
        System.out.println(foo(mycbuf, 0, 50));
        System.out.println(Arrays.toString(mycbuf));

    }

    private static int foo(char[] cbuf, int off, int len) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() <= len) {
            sb.append(UUID.randomUUID().toString());
        }
        String decrypted = sb.toString();

        for (int i = 0; i < len; i++) {
            cbuf[off + i] = decrypted.charAt(i);
        }

        return len;
    }


    private static void createTeam() {
        //tutaj wczytywanie wszystkich parametrów zespołu
    }

    private static void createMatch() {

    }


}

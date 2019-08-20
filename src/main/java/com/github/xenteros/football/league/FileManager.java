package com.github.xenteros.football.league;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class FileManager {

    public static List<Team> readTeamsFromFile() {

        return null;
    }

    public static void dumpTeamsToFile(List<Team> teams) throws IOException {
        CSVWriter writer = new CSVWriter(
                new FileWriter("teams.csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(teams.stream()
                .map(FileManager::teamToArray)
                .peek(team -> System.out.println(Arrays.toString(team)))
                .peek(System.out::println)
                .collect(toList())
        );

        writer.close();

    }


    public static void dumpMatchesToFile(List<Match> matches) throws IOException {
        CSVWriter writer = new CSVWriter(
                new FileWriter("matches.csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(matches.stream()
                .map(FileManager::matchToArray)
                .collect(toList()));
        writer.close();
    }

    public static List<String[]> readCSVFile() throws IOException {
        CSVReader reader = new CSVReader(
                new FileReader("matches.csv"),
                ';',
                '"',
                '\\'
        );
        return reader.readAll();
    }

    private static String[] matchToArray(Match match) {
        return new String[] {
                match.getHost().getName(),
                match.getAway().getName(),
                match.getResult()};
    }

    private static String[] teamToArray(Team team) {
        return new String[] {
                String.valueOf(team.getId()),
                team.getName(),
                String.join(",", team.getPlayers())
        };
    }
}

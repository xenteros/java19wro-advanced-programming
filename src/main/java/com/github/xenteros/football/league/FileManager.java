package com.github.xenteros.football.league;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class FileManager {

    public static List<Team> readTeamsFromFile() throws IOException {
        CSVParserBuilder parserBuilder = new CSVParserBuilder() // parser builder do parametrów
                .withEscapeChar('\\')
                .withIgnoreLeadingWhiteSpace(true)
                .withQuoteChar('"')
                .withSeparator(';');

        CSVReaderBuilder readerBuilder = new CSVReaderBuilder(new FileReader("teams.csv")).withCSVParser(parserBuilder.build());
        CSVReader reader = readerBuilder.build();
        return reader.readAll().stream()
                .map(FileManager::arrayToTeam)
                .collect(toList());
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
        return new String[]{
                String.valueOf(match.getHost().getId()),
                String.valueOf(match.getAway().getId()),
                match.getResult()};
    }

    private static String[] teamToArray(Team team) {
        return new String[]{
                String.valueOf(team.getId()),
                team.getName(),
                String.join(",", team.getPlayers())
        };
    }

    private static Team arrayToTeam(String[] row) {
        int id = Integer.parseInt(row[0]);
        String teamName = row[1];
        List<String> players = Arrays.asList(row[2].split(","));
        return new Team(id, players, teamName);
    }
}

package com.github.xenteros.football.league;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import lombok.extern.log4j.Log4j;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Log4j
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

    public static List<Match> readMatchesFromFile(Map<Integer, Team> availableTeams) throws IOException {
        CSVReader reader = new CSVReader(
                new FileReader("matches.csv"),
                ';',
                '"',
                '\\'
        );
        return reader.readAll().stream()
                .map(row -> arrayToMatch(row, availableTeams))
                .collect(toList());
    }

    private static String[] matchToArray(Match match) {
        return new String[]{
                String.valueOf(match.getId()),
                String.valueOf(match.getHost().getId()),
                String.valueOf(match.getAway().getId()),
                match.getResult()};
    }

    private static Match arrayToMatch(String[] row, Map<Integer, Team> availableTeams) {
        int id = Integer.parseInt(row[0]);
        int hostId = Integer.parseInt(row[1]);
        int awayId = Integer.parseInt(row[2]);
        String score = row[3];

        Team host = availableTeams.get(hostId);
        Team away = availableTeams.get(awayId);

        if (host == null) {
            log.error(String.format("Host with id %d not found.", hostId));
            throw new RuntimeException("Host not found");
        } if (away == null) {
            log.error(String.format("Away with id %d not found.", awayId));
            throw new RuntimeException("Away not found");
        }

        return new Match(id, host, away, score);

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

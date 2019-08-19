package com.github.xenteros.football.league;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

class LeagueManager {


    public static void main(String[] args) throws IOException {

        Team one = new Team(1,null, "one");
        Team two = new Team(2, null, "two");
        Team three = new Team(3, null, "three");

        List<Match> matches = Arrays.asList(
                new Match(one, two, "1:2"),
                new Match(one, two, "1:3"),
                new Match(one, three, "2:3"),
                new Match(two, three, "4:3"),
                new Match(two, one, "1:3"),
                new Match(one, two, "1:4")
        );
//        dumpMatchesToFile(matches);
        readCSVFile().forEach(array -> System.out.println(Arrays.toString(array)));
    }

    public static void dumpMatchesToFile(List<Match> matches) throws IOException {
        CSVWriter writer = new CSVWriter(
                new FileWriter("matches.csv"),
                ';',
                '"',
                '\\',
                "\n");

        writer.writeAll(matches.stream()
                .map(LeagueManager::matchToArray)
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
        return new String[] {match.getHost().getName(), match.getAway().getName(), match.getResult()};
    }
}

package com.github.xenteros.football.league;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

class LeagueManager {


    public static void main(String[] args) throws IOException {

        Team one = new Team(1, emptyList(), "on\"e");
        Team two = new Team(2, emptyList(),"two");
        Team three = new Team(3, emptyList(), "three");
        Team four = Team.builder()
                .id(4)
                .name("four")
                .players(emptyList())
                .build();

        List<Team> teams = Arrays.asList(one, two, three, four);

        List<Match> matches = Arrays.asList(
                new Match(one, two, "1:2"),
                new Match(one, two, "1:3"),
                new Match(one, three, "2:3"),
                new Match(two, three, "4:3"),
                new Match(two, one, "1:3"),
                new Match(one, two, "1:4")
        );

        FileManager.dumpTeamsToFile(teams);
        FileManager.dumpMatchesToFile(matches);

        List<Team> restoredTeams = FileManager.readTeamsFromFile();

        Map<Integer, Team> teamsById = restoredTeams.stream()
                .collect(Collectors.toMap(Team::getId, Function.identity()));

        List<Match> restoredMatches = FileManager.readMatchesFromFile(teamsById);

        restoredMatches.forEach(System.out::println);

    }


}

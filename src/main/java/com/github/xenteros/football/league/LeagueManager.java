package com.github.xenteros.football.league;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

class LeagueManager {


    public static void main(String[] args) throws IOException {

        Team one = new Team(1, emptyList(), "on\"e");
        Team two = new Team(2, emptyList(),"two");
        Team three = new Team(3, emptyList(), "three");

        System.out.println(one.getName());

        FileManager.dumpTeamsToFile(Arrays.asList(one, two, three));

        List<Match> matches = Arrays.asList(
                new Match(one, two, "1:2"),
                new Match(one, two, "1:3"),
                new Match(one, three, "2:3"),
                new Match(two, three, "4:3"),
                new Match(two, one, "1:3"),
                new Match(one, two, "1:4")
        );
//        dumpMatchesToFile(matches);
        FileManager.readCSVFile().forEach(array -> System.out.println(Arrays.toString(array)));
    }


}

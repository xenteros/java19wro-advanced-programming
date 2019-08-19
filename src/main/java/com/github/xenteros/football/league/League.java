package com.github.xenteros.football.league;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Klasa reprezentująca ligę. Przechowuje listę drużyn i listę meczów.
 * Potrafi zwrócić listę drużyn posortowaną po zdobytych punktach (3-1-0).
 */
class League {

    private List<Team> teams;
    private List<Match> matches;

    public League(List<Team> teams, List<Match> matches) {
        this.teams = teams;
        this.matches = matches;
    }

    public List<Team> standing() {

        Map<Team, List<Match>> teamMatchMap = new HashMap<>();
        for (Team team : teams) {
            List<Match> matches = this.matches.stream()
                    .filter(match -> match.has(team))
                    .collect(toList());
            teamMatchMap.put(team, matches);
        }

        Map<Team, Integer> scores = new HashMap<>();
        for (Team team : teams) {
            int sum = teamMatchMap.get(team).stream()
                    .mapToInt(match -> match.getPoints(team))
                    .sum();
            scores.put(team, sum);
        }

        return scores.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .collect(toList());
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}

package com.github.xenteros.football.league;


import java.util.Arrays;
import java.util.List;

/**
 * Przechowuje listę zawodników (załóżmy sobie, że imię i nazwisko zawodnika jest unikalne),
 * ma nazwę.
 */
class Team {

    private List<String> players;
    private String name;

    public Team() {
    }

    public Team(List<String> players, String name) {
        this.players = players;
        this.name = name;
    }

    public static Team languages() {
        Team team = new Team();
        team.setName("languages");
        team.setPlayers(Arrays.asList("Java", "Python", "C++"));
        return team;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

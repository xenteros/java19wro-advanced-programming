package com.github.xenteros.football.league;


import java.util.List;

/**
 * Przechowuje listę zawodników (załóżmy sobie, że imię i nazwisko zawodnika jest unikalne),
 * ma nazwę.
 */
class Team {

    private final int id;
    private List<String> players;
    private String name;


    public Team(int id, List<String> players, String name) {
        this.id = id;
        this.players = players;
        this.name = name;
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

    public int getId() {
        return id;
    }


}

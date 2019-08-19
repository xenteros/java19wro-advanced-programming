package com.github.xenteros.football.league;


/**
 * Reprezentuje konkretny, zakończony pojedynek.
 * Ma gospodarza (host), gościa (away) i wynik i listę strzelców bramek.
 */
class Match {

    private Team host;
    private Team away;
    private String result;

    public Match() {
    }

    public Match(Team host, Team away, String result) {
        this.host = host;
        this.away = away;
        this.result = result;
    }

    public Team getHost() {
        return host;
    }

    public void setHost(Team host) {
        this.host = host;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

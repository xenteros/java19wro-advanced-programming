package com.github.xenteros.football.league;


import lombok.ToString;

/**
 * Reprezentuje konkretny, zakończony pojedynek.
 * Ma gospodarza (host), gościa (away) i wynik i listę strzelców bramek.
 */
@ToString
class Match {

    private int id;
    private Team host;
    private Team away;
    private String result;
//    private int hostScore;
//    private int awayScore;

    public Match() {
    }

    public Match(int id, Team host, Team away, String result) {
        this.id = id;
        this.host = host;
        this.away = away;
        this.result = result;
    }

    public boolean has(Team team) {
        return host.equals(team) || away.equals(team);
    }

    public int getPoints(Team team) {

        if (!(this.has(team))) {
            throw new RuntimeException();
        }

        String[] resultSplit = result.split(":");
        int hostScore = Integer.parseInt(resultSplit[0]);
        int awayScore = Integer.parseInt(resultSplit[1]);
        if (hostScore == awayScore) {
            return 1;
        }
        if (hostScore > awayScore) {
            return host.equals(team) ? 3 : 0;
        } else {
            return away.equals(team) ? 3 : 0;
        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

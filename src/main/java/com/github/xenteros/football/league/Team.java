package com.github.xenteros.football.league;


import lombok.*;

import java.util.List;

/**
 * Przechowuje listę zawodników (załóżmy sobie, że imię i nazwisko zawodnika jest unikalne),
 * ma nazwę.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
class Team {

    private int id;
    private List<String> players;
    public String name;


}

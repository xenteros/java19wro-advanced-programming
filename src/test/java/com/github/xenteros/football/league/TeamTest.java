package com.github.xenteros.football.league;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class TeamTest {


    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Team.class).verify();
    }
}
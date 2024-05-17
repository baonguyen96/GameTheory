package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.impl.AlwaysCooperate;
import org.gametheory.strategy.impl.AlwaysDefect;
import org.gametheory.tournament.TournamentConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RoundRobinTournamentTest {
    private Tournament tournament;

    @Before
    public void setUp() {
        Player cooperatePlayer = new Player(new AlwaysCooperate());
        Player defectPlayer = new Player(new AlwaysDefect());
        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(Arrays.asList(cooperatePlayer, defectPlayer))
                .withShowMatches(true)
                .build();
        tournament = new RoundRobinTournament(tournamentConfig);
    }

    @Test
    public void start() {
        int matches  = tournament.start();
        assertEquals(1, matches);
    }
}
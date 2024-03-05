package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.AlwaysCooperate;
import org.gametheory.strategy.impl.AlwaysDefect;
import org.gametheory.strategy.impl.DefectAtInterval;
import org.gametheory.tournament.TournamentConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OneVsOneTournamentTest {
    private Tournament tournament;

    @Before
    public void setUp() throws Exception {
        Player coopPlayer = new Player(new AlwaysCooperate());
        Player defectPlayer = new Player(new AlwaysDefect());
        Player defectAtIntervalPlayer = new Player(new DefectAtInterval());
        List<Player> players = Arrays.asList(coopPlayer, defectPlayer, defectAtIntervalPlayer);
        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withPlayerIdToAgainstTheRest(coopPlayer.getPlayerId())
                .build();

        tournament = new OneVsOneTournament(tournamentConfig);
    }

    @Test
    public void start() {
        int matches = tournament.start();
        assertEquals(2, matches);
    }

    @Test
    public void getWinner() {
        tournament.start();
        assertNull(tournament.getWinner());
    }
}
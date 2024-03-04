package org.gametheory.tournament;

import org.gametheory.player.Player;
import org.gametheory.strategy.impl.AlwaysCooperate;
import org.gametheory.strategy.impl.AlwaysDefect;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TournamentTest {
    private Tournament tournament;
    private Player cooperatePlayer;
    private Player deflectPlayer;

    @Before
    public void setUp() {
        cooperatePlayer = new Player(new AlwaysCooperate());
        deflectPlayer = new Player(new AlwaysDefect());
        TournamentConfig tournamentConfig = new TournamentConfig(Arrays.asList(cooperatePlayer, deflectPlayer), 1, 1, true, 10);
        tournament = new Tournament(tournamentConfig);
    }

    @Test
    public void start() {
        int matches  = tournament.start();
        assertEquals(1, matches);
    }

    @Test
    public void getWinner() {
        tournament.start();
        Player winner = tournament.getWinner();
        assertEquals(deflectPlayer.getPlayerId(), winner.getPlayerId());
    }
}
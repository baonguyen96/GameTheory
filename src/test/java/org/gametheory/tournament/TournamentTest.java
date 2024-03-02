package org.gametheory.tournament;

import org.gametheory.player.Player;
import org.gametheory.strategy.impl.AlwaysCooperateStrategy;
import org.gametheory.strategy.impl.AlwaysDeflectStrategy;
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
        cooperatePlayer = new Player(new AlwaysCooperateStrategy());
        deflectPlayer = new Player(new AlwaysDeflectStrategy());
        tournament = new Tournament(1, Arrays.asList(cooperatePlayer, deflectPlayer));
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
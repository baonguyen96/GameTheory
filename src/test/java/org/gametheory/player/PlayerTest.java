package org.gametheory.player;


import org.gametheory.strategy.Strategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = Mockito.mock(Strategy.class);
        player = new Player(strategy);
    }

    @After
    public void tearDown() {
        player = null;
    }

    @Test
    public void playFirstMove() {
        Player opponent = new Player(strategy);
        Mockito.when(strategy.makeFirstMove()).thenReturn(Strategy.Move.COOPERATE);
        Mockito.when(strategy.makeMove(Mockito.anyList())).thenReturn(Strategy.Move.DEFECT);
        Strategy.Move move = player.play(opponent);
        assertEquals(Strategy.Move.COOPERATE, move);
    }

    @Test
    public void playNextMove() {
        Player opponent = Mockito.mock(Player.class);
        Mockito.when(opponent.getLastMoves()).thenReturn(Collections.singletonList(Strategy.Move.DEFECT));
        Mockito.when(strategy.makeFirstMove()).thenReturn(Strategy.Move.COOPERATE);
        Mockito.when(strategy.makeMove(Mockito.any())).thenReturn(Strategy.Move.DEFECT);
        player.play(opponent);
        Strategy.Move move = player.play(opponent);
        assertEquals(Strategy.Move.DEFECT, move);
    }

    @Test
    public void increaseScore() {
        player.increaseScore(1);
        assertEquals(1, player.getScore());
    }

    @Test
    public void getPlayerId() {
        assertTrue(player.getPlayerId() > 0);
    }

    @Test
    public void equals() {
        assertFalse(player.equals(new Player(null)));
    }
}
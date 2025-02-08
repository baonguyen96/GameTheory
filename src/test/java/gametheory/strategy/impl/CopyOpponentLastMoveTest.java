package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.strategy.impl.CopyOpponentLastMove;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CopyOpponentLastMoveTest {
    private Strategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new CopyOpponentLastMove();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.DEFECT)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE)));
    }
}
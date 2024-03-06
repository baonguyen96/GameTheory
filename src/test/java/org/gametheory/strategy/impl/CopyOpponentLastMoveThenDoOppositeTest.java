package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CopyOpponentLastMoveThenDoOppositeTest {
    private Strategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new CopyOpponentLastMoveThenDoOpposite();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.DEFECT)));
    }
}
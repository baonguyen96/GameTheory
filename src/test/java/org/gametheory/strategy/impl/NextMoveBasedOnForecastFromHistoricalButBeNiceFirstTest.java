package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NextMoveBasedOnForecastFromHistoricalButBeNiceFirstTest {
    private Strategy strategy;
    @Before
    public void setUp() throws Exception {
        strategy = new NextMoveBasedOnForecastFromHistoricalButBeNiceFirst();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void isNice() {
        assertTrue(strategy.isNice());
    }
}
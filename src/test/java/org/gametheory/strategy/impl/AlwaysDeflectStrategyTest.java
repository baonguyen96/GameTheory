package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AlwaysDeflectStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new AlwaysDeflectStrategy();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.DEFLECT, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.DEFLECT, strategy.makeMove(Collections.emptyList()));
    }
}
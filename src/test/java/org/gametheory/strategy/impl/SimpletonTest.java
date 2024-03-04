package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SimpletonTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new Simpleton();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove_opponent() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
    }

    @Test
    public void makeMove_continueLastMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.singletonList(Strategy.Move.DEFECT), Collections.singletonList(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE), Collections.singletonList(Strategy.Move.COOPERATE)));
    }

    @Test
    public void makeMove_oppositeLastMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.DEFECT), Collections.singletonList(Strategy.Move.DEFECT)));
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE), Collections.singletonList(Strategy.Move.DEFECT)));
    }
}
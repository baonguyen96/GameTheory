package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RandomMoveTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new RandomMove();
    }

    @Test
    public void makeFirstMove() {
        Strategy.Move move = strategy.makeFirstMove();
        assertTrue(move == Strategy.Move.DEFECT || move == Strategy.Move.COOPERATE);
    }

    @Test
    public void makeMove() {
        Strategy.Move move = strategy.makeMove(Collections.emptyList());
        assertTrue(move == Strategy.Move.DEFECT || move == Strategy.Move.COOPERATE);
    }

    @Test
    public void isNice() {
        assertFalse(strategy.isNice());
    }
}
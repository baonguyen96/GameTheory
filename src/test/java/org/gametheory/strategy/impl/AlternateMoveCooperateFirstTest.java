package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AlternateMoveCooperateFirstTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new AlternateMoveCooperateFirst();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove_simple() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.emptyList()));
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.DEFECT), Collections.emptyList()));
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE), Collections.emptyList()));
    }
}
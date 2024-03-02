package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class OnlyRetaliateIfBeingAttackedConsecutivelyStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new OnlyRetaliateIfBeingBeingAttackedConsecutivelyStrategy();
    }

    @Test
    public void makeMove_cooperate() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE)));
    }

    @Test
    public void makeMove_defect() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.DEFECT)));
    }
}
package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class OnlyRetaliateIfAttackedConsecutivelyStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new OnlyRetaliateIfAttackedConsecutivelyStrategy();
    }

    @Test
    public void makeMove_cooperate() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Arrays.asList(Strategy.Move.DEFLECT, Strategy.Move.COOPERATE)));
    }

    @Test
    public void makeMove_deflect() {
        assertEquals(Strategy.Move.DEFLECT, strategy.makeMove(Arrays.asList(Strategy.Move.DEFLECT, Strategy.Move.DEFLECT)));
    }
}
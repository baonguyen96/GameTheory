package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OnlyRetaliateIfAttackedConsecutivelyStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new OnlyRetaliateIfAttackedConsecutivelyStrategy();
    }

    @Test
    public void makeMove_cooperate() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(List.of()));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(List.of(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(List.of(Strategy.Move.DEFLECT, Strategy.Move.COOPERATE)));
    }

    @Test
    public void makeMove_deflect() {
        assertEquals(Strategy.Move.DEFLECT, strategy.makeMove(List.of(Strategy.Move.DEFLECT, Strategy.Move.DEFLECT)));
    }
}
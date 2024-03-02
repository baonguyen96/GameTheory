package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OnlyRetaliateIfBeingAttackedButAttackOnFirstMoveStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new OnlyRetaliateIfBeingBeingAttackedButAttackOnFirstMoveStrategy();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeFirstMove());
    }
}
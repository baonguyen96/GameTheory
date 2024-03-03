package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefectFirstAndKeepDefectIfNotRetaliatedTest {
    private Strategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new DefectFirstAndKeepDefectIfNotRetaliated();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeFirstMove());
    }
}
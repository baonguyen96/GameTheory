package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.strategy.impl.AlwaysDefect;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AlwaysDefectTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new AlwaysDefect();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.emptyList()));
    }
}
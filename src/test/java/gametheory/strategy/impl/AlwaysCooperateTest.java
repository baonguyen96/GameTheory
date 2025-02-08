package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.strategy.impl.AlwaysCooperate;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AlwaysCooperateTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new AlwaysCooperate();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
    }
}
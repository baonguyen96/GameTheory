package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.strategy.impl.OnlyRetaliateIfBeingAttacked;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class OnlyRetaliateIfBeingAttackedTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new OnlyRetaliateIfBeingAttacked();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove_cooperate() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
    }

    @Test
    public void makeMove_defect() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.singletonList(Strategy.Move.DEFECT)));
    }
}
package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.strategy.impl.CooperateFirstButWillDefectTwiceForEachAttack;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class CooperateFirstButWillDefectTwiceForEachAttackTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new CooperateFirstButWillDefectTwiceForEachAttack();
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.singletonList(Strategy.Move.DEFECT)));
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE)));
    }
}
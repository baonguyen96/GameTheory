package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.strategy.impl.OnlyRetaliateIfBeingAttackedButExtraForgiveOnceInAWhile;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class OnlyRetaliateIfBeingAttackedButExtraForgiveOnceInAWhileTest {
    private Strategy strategy;

    @Before
    public void setUp() {
        strategy = new OnlyRetaliateIfBeingAttackedButExtraForgiveOnceInAWhile();
    }

    @Test
    public void makeMove_cooperate() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList(), Collections.singletonList(Strategy.Move.COOPERATE)));
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList(), Collections.emptyList()));

        List<Strategy.Move> moves = IntStream.range(0, 10).mapToObj(i -> Strategy.Move.DEFECT).collect(Collectors.toList());
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(moves, moves));
    }

    @Test
    public void makeMove_defect() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Collections.emptyList(), Collections.singletonList(Strategy.Move.DEFECT)));
    }
}
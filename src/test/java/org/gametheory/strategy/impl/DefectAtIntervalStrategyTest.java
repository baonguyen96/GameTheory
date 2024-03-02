package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class DefectAtIntervalStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new DefectAtIntervalStrategy(50);
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.DEFECT)));

        List<Strategy.Move> moves = IntStream.range(0, 50).mapToObj(i -> Strategy.Move.COOPERATE).collect(Collectors.toList());
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(moves));
    }
}
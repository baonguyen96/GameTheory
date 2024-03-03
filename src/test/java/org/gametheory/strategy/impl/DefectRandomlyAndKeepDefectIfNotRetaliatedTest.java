package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class DefectRandomlyAndKeepDefectIfNotRetaliatedTest {
    private Strategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new DefectRandomlyAndKeepDefectIfNotRetaliated();
    }

    @Test
    public void makeMove_random() {
        Strategy.Move move = strategy.makeMove(Collections.emptyList());
        assertTrue(move == Strategy.Move.DEFECT || move == Strategy.Move.COOPERATE);
    }

    @Test
    public void makeMove_takeAdvantageOfDefect() {
        List<Strategy.Move> opponentLastMoves = IntStream.range(0, 50).mapToObj(i -> Strategy.Move.COOPERATE).collect(Collectors.toList());
        List<Strategy.Move> myLastMoves = IntStream.range(0, 50).mapToObj(i -> Strategy.Move.DEFECT).collect(Collectors.toList());
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.DEFECT, move);
    }
}
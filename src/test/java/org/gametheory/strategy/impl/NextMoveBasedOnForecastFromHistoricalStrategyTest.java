package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NextMoveBasedOnForecastFromHistoricalStrategyTest {
    private Strategy strategy;

    @Before
    public void setUp() throws Exception {
        strategy = new NextMoveBasedOnForecastFromHistoricalStrategy();
    }

    @Test
    public void makeMove_defect_dueToIDefectOpponentCooperate() {
        List<Strategy.Move> myLastMoves = Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.DEFECT);
        List<Strategy.Move> opponentLastMoves = Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE);
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.DEFECT, move);
    }

    @Test
    public void makeMove_defect_dueToIDefectOpponentDefect() {
        List<Strategy.Move> myLastMoves = Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.DEFECT, Strategy.Move.DEFECT);
        List<Strategy.Move> opponentLastMoves = Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE, Strategy.Move.DEFECT);
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.DEFECT, move);
    }

    @Test
    public void makeMove_cooperate_dueToICooperateOpponentCooperate() {
        List<Strategy.Move> myLastMoves = Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.DEFECT, Strategy.Move.COOPERATE);
        List<Strategy.Move> opponentLastMoves = Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE, Strategy.Move.COOPERATE);
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.COOPERATE, move);
    }

    @Test
    public void makeMove_defect_dueToICooperateOpponentDefect() {
        List<Strategy.Move> myLastMoves = Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.DEFECT, Strategy.Move.COOPERATE);
        List<Strategy.Move> opponentLastMoves = Arrays.asList(Strategy.Move.DEFECT, Strategy.Move.COOPERATE, Strategy.Move.DEFECT);
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.DEFECT, move);
    }
}
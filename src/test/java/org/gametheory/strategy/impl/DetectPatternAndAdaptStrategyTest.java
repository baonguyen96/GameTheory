package org.gametheory.strategy.impl;

import org.apache.commons.collections.ListUtils;
import org.gametheory.strategy.Strategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class DetectPatternAndAdaptStrategyTest {
    private Strategy strategy;
    private List<Strategy.Move> myLastMoves;
    private List<Strategy.Move> opponentLastMoves;

    @Before
    public void setUp() throws Exception {
        strategy = new DetectPatternAndAdaptStrategy();
        myLastMoves = Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE, Strategy.Move.DEFECT, Strategy.Move.COOPERATE);
        opponentLastMoves = Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE, Strategy.Move.DEFECT, Strategy.Move.COOPERATE);
    }

    @Test
    public void makeFirstMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeFirstMove());
    }

    @Test
    public void makeMove() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.emptyList()));
    }

    @Test
    public void makeMove_2nd() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Collections.singletonList(Strategy.Move.COOPERATE), Collections.emptyList()));
    }

    @Test
    public void makeMove_3rd() {
        assertEquals(Strategy.Move.DEFECT, strategy.makeMove(Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE), Collections.emptyList()));
    }

    @Test
    public void makeMove_4th() {
        assertEquals(Strategy.Move.COOPERATE, strategy.makeMove(Arrays.asList(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE, Strategy.Move.DEFECT), Collections.emptyList()));
    }

    @Test
    public void testMakeMove_defectToTakeAdvantage() {
        List<Strategy.Move> myLastMoves = ListUtils.union(this.myLastMoves, Collections.singletonList(Strategy.Move.DEFECT));
        List<Strategy.Move> opponentLastMoves = ListUtils.union(this.opponentLastMoves, Collections.singletonList(Strategy.Move.COOPERATE));
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.DEFECT, move);
    }

    @Test
    public void testMakeMove_cooperateSinceBothDefected() {
        List<Strategy.Move> myLastMoves = ListUtils.union(this.myLastMoves, Collections.singletonList(Strategy.Move.DEFECT));
        List<Strategy.Move> opponentLastMoves = ListUtils.union(this.opponentLastMoves, Collections.singletonList(Strategy.Move.DEFECT));
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.COOPERATE, move);
    }

    @Test
    public void testMakeMove_defectToRetaliate() {
        List<Strategy.Move> myLastMoves = ListUtils.union(this.myLastMoves, Collections.singletonList(Strategy.Move.COOPERATE));
        List<Strategy.Move> opponentLastMoves = ListUtils.union(this.opponentLastMoves, Collections.singletonList(Strategy.Move.DEFECT));
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.DEFECT, move);
    }

    @Test
    public void testMakeMove_cooperate() {
        List<Strategy.Move> myLastMoves = ListUtils.union(this.myLastMoves, Collections.singletonList(Strategy.Move.COOPERATE));
        List<Strategy.Move> opponentLastMoves = ListUtils.union(this.opponentLastMoves, Collections.singletonList(Strategy.Move.COOPERATE));
        Strategy.Move move = strategy.makeMove(myLastMoves, opponentLastMoves);
        assertEquals(Strategy.Move.COOPERATE, move);
    }
}
package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class DefectAtIntervalStrategy implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return opponentLastMoves != null && opponentLastMoves.size() % 50 == 0 ? Move.DEFECT : Move.COOPERATE;
    }
}

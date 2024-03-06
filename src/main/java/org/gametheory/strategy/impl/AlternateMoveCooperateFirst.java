package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class AlternateMoveCooperateFirst implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return Move.DEFECT;
    }

    @Override
    public Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        Move myLastMove = myLastMoves.isEmpty() ? null : myLastMoves.get(myLastMoves.size() - 1);
        return opposite(myLastMove);
    }
}

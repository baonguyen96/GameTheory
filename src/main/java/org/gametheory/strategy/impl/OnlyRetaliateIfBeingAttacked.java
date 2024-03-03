package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class OnlyRetaliateIfBeingAttacked implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        Move opponentLastMove = opponentLastMoves.isEmpty() ? null : opponentLastMoves.get(opponentLastMoves.size() - 1);
        return opponentLastMove == Move.DEFECT ? Move.DEFECT : Move.COOPERATE;
    }
}

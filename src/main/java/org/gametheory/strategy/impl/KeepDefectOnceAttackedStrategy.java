package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class KeepDefectOnceAttackedStrategy implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return opponentLastMoves.stream().anyMatch(move -> move == Move.DEFECT) ? Move.DEFECT : Move.COOPERATE;
    }
}

package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class AlwaysDefect implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return Move.DEFECT;
    }
}

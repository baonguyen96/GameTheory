package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class AlwaysDeflectStrategy implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.DEFLECT;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return Move.DEFLECT;
    }
}

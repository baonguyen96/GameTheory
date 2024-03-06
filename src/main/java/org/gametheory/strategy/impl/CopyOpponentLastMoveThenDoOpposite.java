package org.gametheory.strategy.impl;

import java.util.List;

public class CopyOpponentLastMoveThenDoOpposite extends CopyOpponentLastMove {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        Move move = super.makeMove(opponentLastMoves);
        return opposite(move);
    }
}

package org.gametheory.strategy.impl;

import java.util.List;

public class AlternateMoveDefectFirst extends AlternateMoveCooperateFirst {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return Move.COOPERATE;
    }
}

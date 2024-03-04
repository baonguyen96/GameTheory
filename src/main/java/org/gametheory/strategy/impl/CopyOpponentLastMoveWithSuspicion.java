package org.gametheory.strategy.impl;

public class CopyOpponentLastMoveWithSuspicion extends CopyOpponentLastMove {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

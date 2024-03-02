package org.gametheory.strategy.impl;

import java.util.List;

public class DefectRandomlyAndKeepDefectIfNotRetaliatedStrategy extends RandomMoveStrategy {
    @Override
    public Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        Move move = super.makeMove(opponentLastMoves);
        Move opponentLastMove = opponentLastMoves.isEmpty() ? null : opponentLastMoves.get(opponentLastMoves.size() - 1);
        Move myLastMove = myLastMoves.isEmpty() ? null : myLastMoves.get(myLastMoves.size() - 1);

        if (myLastMove == Move.DEFECT && opponentLastMove == Move.COOPERATE) {
            move = Move.DEFECT;
        }

        return move;
    }
}

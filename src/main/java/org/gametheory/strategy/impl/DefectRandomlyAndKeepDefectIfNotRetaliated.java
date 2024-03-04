package org.gametheory.strategy.impl;

import java.util.List;

public class DefectRandomlyAndKeepDefectIfNotRetaliated extends RandomMove {
    @Override
    public Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        Move move = super.makeRandomMove();
        Move opponentLastMove = opponentLastMoves.isEmpty() ? null : opponentLastMoves.get(opponentLastMoves.size() - 1);
        Move myLastMove = myLastMoves.isEmpty() ? null : myLastMoves.get(myLastMoves.size() - 1);

        if (myLastMove == Move.DEFECT && opponentLastMove == Move.COOPERATE) {
            move = Move.DEFECT;
        }

        return move;
    }
}

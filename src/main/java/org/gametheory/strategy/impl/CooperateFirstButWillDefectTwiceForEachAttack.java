package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class CooperateFirstButWillDefectTwiceForEachAttack implements Strategy {
    private int defectMovesLeft = 0;

    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        Move opponentLastMove = opponentLastMoves.isEmpty() ? Move.COOPERATE : opponentLastMoves.get(opponentLastMoves.size() - 1);

        if (opponentLastMove == Move.DEFECT) {
            defectMovesLeft += 2;
        }

        Move move;

        if (defectMovesLeft == 0) {
            move = Move.COOPERATE;
        }
        else {
            move = Move.DEFECT;
            defectMovesLeft--;
        }

        return move;
    }
}

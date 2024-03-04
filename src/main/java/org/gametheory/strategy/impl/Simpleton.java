package org.gametheory.strategy.impl;

import java.util.List;

public class Simpleton extends RandomMove {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        Move move = makeRandomMove();
        Move opponentLastMove = opponentLastMoves.isEmpty() ? makeRandomMove() : opponentLastMoves.get(opponentLastMoves.size() - 1);
        Move myLastMove = myLastMoves.isEmpty() ? move : myLastMoves.get(myLastMoves.size() - 1);

        if (opponentLastMove == Move.COOPERATE) {
            move = myLastMove;
        }
        else if (opponentLastMove == Move.DEFECT) {
            move = myLastMove == Move.COOPERATE ? Move.DEFECT : Move.COOPERATE;
        }

        return move;
    }

    @Override
    public boolean isNice() {
        return true;
    }
}

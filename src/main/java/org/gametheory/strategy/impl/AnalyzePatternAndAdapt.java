package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class AnalyzePatternAndAdapt implements Strategy {
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
        // testing to see how opponent behaves
        if (myLastMoves.size() == 1 || myLastMoves.size() == 3) {
            return Move.COOPERATE;
        }
        else if (myLastMoves.size() == 2) {
            return Move.DEFECT;
        }

        Move move;
        Move opponentLastMove = opponentLastMoves.get(opponentLastMoves.size() - 1);
        Move myLastMove = myLastMoves.get(myLastMoves.size() - 1);

        if (myLastMove == Move.DEFECT && opponentLastMove == Move.COOPERATE) {
            // continue to exploit
            move = Move.DEFECT;
        }
        else if (myLastMove == Move.DEFECT && opponentLastMove == Move.DEFECT) {
            // apologize so that hopefully they will forgive
            move = Move.COOPERATE;
        }
        else if (myLastMove == Move.COOPERATE && opponentLastMove == Move.DEFECT) {
            // retaliate
            move = Move.DEFECT;
        }
        else {
            // let's live in peace
            move = Move.COOPERATE;
        }

        return move;
    }
}

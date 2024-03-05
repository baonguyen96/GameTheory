package org.gametheory.strategy;

import java.util.List;

public interface Strategy {
    enum Move { COOPERATE, DEFECT }

    Move makeFirstMove();

    Move makeMove(List<Move> opponentLastMoves);

    default Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        return makeMove(opponentLastMoves);
    }

    default boolean isNice() {
        return makeFirstMove() == Move.COOPERATE;
    }

    default boolean is(Strategy strategy) {
        if (strategy == null) {
            return false;
        }

        return this.getClass().getName().equals(strategy.getClass().getName());
    }

    default String getName() {
        return this.getClass().getSimpleName();
    }
}

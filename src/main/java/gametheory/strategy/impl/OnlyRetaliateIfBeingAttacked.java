package gametheory.strategy.impl;

import gametheory.strategy.Strategy;

import java.util.List;

public class OnlyRetaliateIfBeingAttacked implements Strategy {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return opponentLastMoves.isEmpty() ? Move.COOPERATE : opponentLastMoves.get(opponentLastMoves.size() - 1);
    }
}

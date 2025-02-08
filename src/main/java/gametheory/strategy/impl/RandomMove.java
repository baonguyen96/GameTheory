package gametheory.strategy.impl;

import gametheory.strategy.Strategy;
import gametheory.utils.Utility;

import java.util.List;

public class RandomMove implements Strategy {
    @Override
    public Move makeFirstMove() {
        return makeRandomMove();
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return makeRandomMove();
    }

    @Override
    public boolean isNice() {
        return false;
    }

    final protected Move makeRandomMove() {
        return Utility.getRandomBoolean() ? Move.COOPERATE : Move.DEFECT;
    }
}

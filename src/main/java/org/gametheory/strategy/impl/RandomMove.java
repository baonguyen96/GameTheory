package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;
import java.util.Random;

public class RandomMove implements Strategy {
    private static final Random random = new Random();

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

    private Move makeRandomMove() {
        int random = RandomMove.random.nextInt() % 2;
        return random == 0 ? Move.COOPERATE : Move.DEFECT;
    }
}

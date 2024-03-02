package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;
import java.util.Random;

public class RandomMoveStrategy implements Strategy {
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
        int random = new Random().nextInt() % 2;
        return random == 0 ? Move.COOPERATE : Move.DEFECT;
    }
}

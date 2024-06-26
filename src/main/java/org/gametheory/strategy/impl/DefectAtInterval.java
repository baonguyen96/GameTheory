package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;
import java.util.Random;

public class DefectAtInterval implements Strategy {
    private final int frequencyToDefect;

    public DefectAtInterval() {
        this(new Random().nextInt(100));
    }

    public DefectAtInterval(int frequencyToDefect) {
        this.frequencyToDefect = Math.max(1, frequencyToDefect);
    }

    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        return opponentLastMoves != null && opponentLastMoves.size() % this.frequencyToDefect == 0
                ? Move.DEFECT : Move.COOPERATE;
    }
}

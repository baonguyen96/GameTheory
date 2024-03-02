package org.gametheory.strategy;

import java.util.List;

public interface Strategy {
    enum Move { COOPERATE, DEFECT}

    Move makeFirstMove();

    Move makeMove(List<Move> opponentLastMoves);
}

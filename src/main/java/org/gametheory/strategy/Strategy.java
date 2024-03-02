package org.gametheory.strategy;

import java.util.List;

public interface Strategy {
    enum Move { COOPERATE, DEFLECT }

    Move makeFirstMove();
    Move makeMove(List<Move> opponentLastMoves);
}

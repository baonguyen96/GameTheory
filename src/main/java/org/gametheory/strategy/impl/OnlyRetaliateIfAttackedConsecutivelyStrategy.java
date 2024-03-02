package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.List;

public class OnlyRetaliateIfAttackedConsecutivelyStrategy extends OnlyRetaliateIfAttackedStrategy {
    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        if (opponentLastMoves.size() < 2) {
            return Move.COOPERATE;
        }

        boolean areLast2MovesDeflect = opponentLastMoves
                .subList(opponentLastMoves.size() - 2, opponentLastMoves.size() - 1)
                .stream()
                .allMatch(move -> move == Move.DEFLECT);

        return areLast2MovesDeflect ? Move.DEFLECT : Move.COOPERATE;
    }
}

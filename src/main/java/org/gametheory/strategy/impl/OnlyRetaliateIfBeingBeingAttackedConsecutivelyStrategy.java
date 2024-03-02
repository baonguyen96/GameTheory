package org.gametheory.strategy.impl;

import java.util.List;

public class OnlyRetaliateIfBeingBeingAttackedConsecutivelyStrategy extends OnlyRetaliateIfBeingAttackedStrategy {
    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        if (opponentLastMoves.size() < 2) {
            return Move.COOPERATE;
        }

        boolean areLast2MovesDeflect = opponentLastMoves
                .subList(opponentLastMoves.size() - 2, opponentLastMoves.size())
                .stream()
                .allMatch(move -> move == Move.DEFECT);

        return areLast2MovesDeflect ? Move.DEFECT : Move.COOPERATE;
    }
}

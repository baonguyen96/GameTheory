package org.gametheory.strategy.impl;

import java.util.List;

public class OnlyRetaliateIfBeingAttackedConsecutively extends OnlyRetaliateIfBeingAttacked {
    @Override
    public Move makeMove(List<Move> opponentLastMoves) {
        if (opponentLastMoves.size() < 2) {
            return Move.COOPERATE;
        }

        boolean areLast2MovesDefected = opponentLastMoves
                .subList(opponentLastMoves.size() - 2, opponentLastMoves.size())
                .stream()
                .allMatch(move -> move == Move.DEFECT);

        return areLast2MovesDefected ? Move.DEFECT : Move.COOPERATE;
    }
}

package gametheory.strategy.impl;


import java.util.List;

public class OnlyRetaliateIfBeingAttackedButExtraForgiveOnceInAWhile extends OnlyRetaliateIfBeingAttacked {
    @Override
    public Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        Move move = super.makeMove(myLastMoves, opponentLastMoves);

        if (move == Move.DEFECT
                && myLastMoves.contains(Move.DEFECT)
                && myLastMoves.stream().filter(previousMove -> previousMove == Move.DEFECT).count() % 10 == 0) {
            move = Move.COOPERATE;
        }

        return move;
    }
}

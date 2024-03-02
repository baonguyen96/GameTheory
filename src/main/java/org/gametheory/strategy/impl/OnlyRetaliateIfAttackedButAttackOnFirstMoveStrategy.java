package org.gametheory.strategy.impl;

public class OnlyRetaliateIfAttackedButAttackOnFirstMoveStrategy extends OnlyRetaliateIfAttackedStrategy {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

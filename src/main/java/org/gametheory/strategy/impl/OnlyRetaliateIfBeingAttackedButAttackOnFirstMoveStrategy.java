package org.gametheory.strategy.impl;

public class OnlyRetaliateIfBeingAttackedButAttackOnFirstMoveStrategy extends OnlyRetaliateIfBeingAttackedStrategy {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

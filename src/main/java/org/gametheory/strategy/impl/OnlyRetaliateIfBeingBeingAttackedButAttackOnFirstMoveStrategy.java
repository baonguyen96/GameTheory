package org.gametheory.strategy.impl;

public class OnlyRetaliateIfBeingBeingAttackedButAttackOnFirstMoveStrategy extends OnlyRetaliateIfBeingAttackedStrategy {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

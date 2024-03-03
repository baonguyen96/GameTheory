package org.gametheory.strategy.impl;

public class OnlyRetaliateIfBeingAttackedButAttackOnFirstMove extends OnlyRetaliateIfBeingAttacked {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

package org.gametheory.strategy.impl;

public class RetaliateIfBeingAttackedButAttackOnFirstMove extends OnlyRetaliateIfBeingAttacked {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

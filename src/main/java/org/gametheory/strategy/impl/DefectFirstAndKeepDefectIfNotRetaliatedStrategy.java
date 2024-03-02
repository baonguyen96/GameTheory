package org.gametheory.strategy.impl;

public class DefectFirstAndKeepDefectIfNotRetaliatedStrategy extends DefectRandomlyAndKeepDefectIfNotRetaliatedStrategy {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

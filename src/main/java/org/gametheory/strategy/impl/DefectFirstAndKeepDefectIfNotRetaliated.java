package org.gametheory.strategy.impl;

public class DefectFirstAndKeepDefectIfNotRetaliated extends DefectRandomlyAndKeepDefectIfNotRetaliated {
    @Override
    public Move makeFirstMove() {
        return Move.DEFECT;
    }
}

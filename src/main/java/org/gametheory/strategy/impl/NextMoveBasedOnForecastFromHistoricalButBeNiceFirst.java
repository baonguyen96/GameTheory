package org.gametheory.strategy.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextMoveBasedOnForecastFromHistoricalButBeNiceFirst extends NextMoveBasedOnForecastFromHistorical {
    @Override
    public Move makeFirstMove() {
        return Move.COOPERATE;
    }

    @Override
    public boolean isNice() {
        return true;
    }
}

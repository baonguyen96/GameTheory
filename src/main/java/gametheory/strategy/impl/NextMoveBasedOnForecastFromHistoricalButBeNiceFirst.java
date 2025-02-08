package gametheory.strategy.impl;

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

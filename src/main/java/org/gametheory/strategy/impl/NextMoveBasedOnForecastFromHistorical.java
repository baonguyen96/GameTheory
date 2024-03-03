package org.gametheory.strategy.impl;

import org.gametheory.strategy.Strategy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextMoveBasedOnForecastFromHistorical extends RandomMove {
    @Override
    public Move makeMove(List<Move> myLastMoves, List<Move> opponentLastMoves) {
        int previousRounds = Math.min(myLastMoves.size(), opponentLastMoves.size());
        int iDefectOpponentCooperate = 0;
        int iDefectOpponentDefect = 0;
        int iCooperateOpponentCooperate = 0;
        int iCooperateOpponentDefect = 0;

        for (int round = 0; round < previousRounds; round++) {
            if (myLastMoves.get(round) == Strategy.Move.DEFECT && opponentLastMoves.get(round) == Strategy.Move.DEFECT) {
                iDefectOpponentDefect++;
            }
            else if (myLastMoves.get(round) == Strategy.Move.DEFECT && opponentLastMoves.get(round) == Strategy.Move.COOPERATE) {
                iDefectOpponentCooperate++;
            }
            else if (myLastMoves.get(round) == Strategy.Move.COOPERATE && opponentLastMoves.get(round) == Strategy.Move.DEFECT) {
                iCooperateOpponentDefect++;
            }
            else {
                iCooperateOpponentCooperate++;
            }
        }

        int maxCombo = Collections.max(Arrays.asList(iDefectOpponentCooperate, iDefectOpponentDefect, iCooperateOpponentCooperate, iCooperateOpponentDefect));
        Move move = null;

        // rank in order of preferable outcome
        if (iDefectOpponentCooperate == maxCombo) {
            move = Move.DEFECT;
        }
        else if (iCooperateOpponentCooperate == maxCombo) {
            // avoid DEFECT as may provoke opponent in next round
            move = Move.COOPERATE;
        }
        else if (iDefectOpponentDefect == maxCombo) {
            move = Move.DEFECT;
        }
        else {
            // only scenario left is I COOPERATE while opponent DEFECT so will end up with 0 points,
            // therefore better off just to DEFECT and save 1 point
            move = Move.DEFECT;
        }

        return move;
    }
}

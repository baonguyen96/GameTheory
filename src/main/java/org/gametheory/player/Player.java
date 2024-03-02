package org.gametheory.player;

import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.Strategy.Move;

import java.util.*;

public class Player {
    private final Strategy strategy;
    private int score = 0;
    private List<Move> lastMoves = new LinkedList<>();
    private static int playerCount = 0;
    private final int playerId;

    public Player(Strategy strategy) {
        this.strategy = strategy;
        this.playerId = playerCount++;
        resetAllMoves();
    }

    public Move play(Player opponent) {
        Move move = (opponent.getLastMoves().isEmpty() || getLastMoves().isEmpty())
                ? strategy.makeFirstMove()
                : strategy.makeMove(this.lastMoves, opponent.lastMoves);

        this.lastMoves.add(move);
        return move;
    }

    public void resetAllMoves() {
        lastMoves = new LinkedList<>();
    }

    public List<Move> getLastMoves() {
        return lastMoves;
    }

    public void increaseScore(int additionalScore) {
        score += additionalScore;
    }

    public int getScore() {
        return score;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public int getPlayerId() {
        return playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;
        return playerId == player.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", strategy=" + strategy.getClass().getSimpleName() +
                ", isNice=" + strategy.isNice() +
                '}';
    }
}

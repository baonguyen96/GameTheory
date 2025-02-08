package gametheory.player;

import gametheory.strategy.Strategy;
import gametheory.strategy.Strategy.Move;
import gametheory.utils.Utility;

import java.util.*;

public class Player implements Cloneable {
    private final Strategy strategy;
    private int score = 0;
    private List<Move> lastMoves = new LinkedList<>();
    private static int playerCount = 0;
    private final int playerId;
    private boolean isConfused;

    public Player(Strategy strategy) {
        this(strategy, false);
    }

    public Player(Strategy strategy, boolean isConfused) {
        this.strategy = strategy;
        this.playerId = playerCount++;
        confuse(isConfused);
        resetAllMoves();
    }

    public void confuse(boolean isConfused) {
        this.isConfused = isConfused;
    }

    public Move play(Player opponent) {
        List<Move> opponentLastMoves = opponent.lastMoves == null ? new ArrayList<>() : new ArrayList<>(opponent.lastMoves);

        if (isConfused && Utility.getRandomBoolean()) {
            Collections.shuffle(opponentLastMoves);
        }

        Move move = (opponent.getLastMoves().isEmpty() || getLastMoves().isEmpty())
                ? strategy.makeFirstMove()
                : strategy.makeMove(this.lastMoves, opponentLastMoves);

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
                ", isConfused=" + isConfused +
                ", strategy=" + strategy.getClass().getSimpleName() +
                ", isNice=" + strategy.isNice() +
                '}';
    }

    @Override
    public Player clone() {
        return new Player(strategy);
    }
}

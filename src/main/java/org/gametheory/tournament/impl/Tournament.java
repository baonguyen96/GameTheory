package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.tournament.TournamentConfig;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Tournament {
    protected final int totalRounds;
    protected List<Player> players;
    protected final boolean showMatches;

    protected enum MatchResult { Win, Tie, Lose }

    public Tournament(TournamentConfig tournamentConfig) {
        this.players = tournamentConfig.getPlayers();
        this.totalRounds = tournamentConfig.getRound();
        this.showMatches = tournamentConfig.isShowMatches();
    }

    public abstract int start();

    final protected MatchResult match(Player player, Player opponent) {
        int playerScoreGained = 0;
        int opponentScoreGained = 0;

        player.resetAllMoves();
        opponent.resetAllMoves();

        for (int round = 0; round < totalRounds; round++) {
            Strategy.Move playerMove = player.play(opponent);
            Strategy.Move opponentMove = opponent.play(player);

            int playerScore = getPlayer1Score(playerMove, opponentMove);
            int opponentScore = getPlayer1Score(opponentMove, playerMove);

            playerScoreGained += playerScore;
            opponentScoreGained += opponentScore;

            player.increaseScore(playerScore);
            opponent.increaseScore(opponentScore);

            if (showMatches) {
                System.out.printf("Round %d : %s (%s) vs %s (%s) : %d vs %d\n",
                        round + 1, player, playerMove, opponent, opponentMove, playerScore, opponentScore);
            }
        }

        MatchResult matchResult;

        if (playerScoreGained > opponentScoreGained) {
            matchResult = MatchResult.Win;
        } else if (playerScoreGained == opponentScoreGained) {
            matchResult = MatchResult.Tie;
        }
        else {
            matchResult = MatchResult.Lose;
        }

        System.out.printf("%s %s against %s : %d vs %d\n", player, matchResult, opponent, playerScoreGained, opponentScoreGained);

        return matchResult;
    }

    static List<Strategy> getDistinctStrategies(List<Player> players) {
        return players
                .stream()
                .map(Player::getStrategy)
                .collect(Collectors.toMap(Strategy::getName, strategy -> strategy, (existing, replacement) -> existing))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    static int getPlayer1Score(Strategy.Move player1Move, Strategy.Move player2Move) {
        if (player1Move == Strategy.Move.DEFECT && player2Move == Strategy.Move.DEFECT) {
            return 1;
        }
        else if (player1Move == Strategy.Move.DEFECT && player2Move == Strategy.Move.COOPERATE) {
            return 5;
        }
        else if (player1Move == Strategy.Move.COOPERATE && player2Move == Strategy.Move.DEFECT) {
            return 0;
        }
        else {
            return 3;
        }
    }

    public Player getWinner() {
        return getWinner(players);
    }

    protected static Player getWinner(List<Player> players) {
        System.out.println("\nScore board:");

        List<Player> topScorers = getRankedPlayers(players);

        for (int rank = 0; rank < topScorers.size(); rank++) {
            Player player = topScorers.get(rank);
            System.out.printf("%d - %s has score: %d\n", rank + 1, player, player.getScore());
        }

        return topScorers.get(0);
    }

    static List<Player> getRankedPlayers(List<Player> players) {
        return players
                .stream()
                .sorted(Comparator
                        .comparingInt(Player::getScore)
                        .reversed()
                        .thenComparingInt(Player::getPlayerId))
                .collect(Collectors.toList());
    }
}

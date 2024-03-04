package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.tournament.TournamentConfig;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Tournament {
    protected final int totalRounds;
    protected List<Player> players;
    protected final boolean showMatches;

    public Tournament(TournamentConfig tournamentConfig) {
        this.players = tournamentConfig.getPlayers();
        this.totalRounds = tournamentConfig.getRound();
        this.showMatches = tournamentConfig.isShowMatches();
    }

    public abstract int start();

    final protected void match(Player player, Player opponent) {
        int playerCurrentScore = player.getScore();
        int opponentCurrentScore = player.getScore();

        player.resetAllMoves();
        opponent.resetAllMoves();

        for (int round = 0; round < totalRounds; round++) {
            Strategy.Move playerMove = player.play(opponent);
            Strategy.Move opponentMove = opponent.play(player);

            int playerScore = getPlayer1Score(playerMove, opponentMove);
            int opponentScore = getPlayer1Score(opponentMove, playerMove);

            player.increaseScore(playerScore);
            opponent.increaseScore(opponentScore);

            if (showMatches) {
                System.out.printf("Round %d : %s (%s) vs %s (%s) : %d vs %d\n",
                        round + 1, player, playerMove, opponent, opponentMove, playerScore, opponentScore);
            }
        }

        int playerScoreGained = player.getScore() - playerCurrentScore;
        int opponentScoreGained = opponent.getScore() - opponentCurrentScore;
        String outcome;

        if (playerScoreGained > opponentScoreGained) {
            outcome = "won";
        } else if (playerScoreGained == opponentScoreGained) {
            outcome = "tied";
        }
        else {
            outcome = "lost";
        }

        System.out.printf("%s %s against %s : %d vs %d\n", player, outcome, opponent, playerScoreGained, opponentScoreGained);
    }

    private static int getPlayer1Score(Strategy.Move player1Move, Strategy.Move player2Move) {
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

    final public Player getWinner() {
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

    protected static List<Player> getRankedPlayers(List<Player> players) {
        return players
                .stream()
                .sorted(Comparator
                        .comparingInt(Player::getScore)
                        .reversed()
                        .thenComparingInt(Player::getPlayerId))
                .collect(Collectors.toList());
    }
}

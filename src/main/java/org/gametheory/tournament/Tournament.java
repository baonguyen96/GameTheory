package org.gametheory.tournament;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tournament {
    protected final int totalRounds;
    protected List<Player> players;
    protected final boolean showMatches;

    public Tournament(TournamentConfig tournamentConfig) {
        this.players = tournamentConfig.getPlayers();
        this.totalRounds = tournamentConfig.getRoundPerCycle();
        this.showMatches = tournamentConfig.isShowMatches();
    }

    public int start() {
        return start(players);
    }

    final protected int start(List<Player> players) {
        List<String> uniqueStrategies =  players
                .stream()
                .map(player -> player.getStrategy().getClass().getSimpleName())
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        System.out.printf("Starting tournament with %d round(s) and %d player(s)\n", totalRounds, players.size());
        System.out.printf("Players population: %d nice, %d not nice\n",
                players.stream().filter(player -> player.getStrategy().isNice()).count(),
                players.stream().filter(player -> !player.getStrategy().isNice()).count());
        System.out.printf("%d unique strategies: %s\n\n", uniqueStrategies.size(), uniqueStrategies);

        int matches = 0;

        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            for (int opponentIndex = playerIndex + 1; opponentIndex < players.size(); opponentIndex++) {
                matches++;

                Player player = players.get(playerIndex);
                Player opponent = players.get(opponentIndex);

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
                        System.out.printf("Round %d : %s (%s) vs Player %s (%s) : %d vs %d\n",
                                round + 1, player, playerMove, opponent, opponentMove, playerScore, opponentScore);
                    }
                }
            }

            if (showMatches) {
                System.out.println();
            }
        }

        System.out.printf("%d-round(s) Tournament ended with %d matches\n", totalRounds, matches);
        return matches;
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

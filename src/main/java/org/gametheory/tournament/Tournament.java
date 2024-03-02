package org.gametheory.tournament;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tournament {
    private final int totalRounds;
    private final List<Player> players;

    public Tournament(int totalRounds, List<Player> players) {
        this.totalRounds = totalRounds;
        this.players = players;
    }

    public int start() {
        System.out.printf("Starting tournament with %d round(s)\n", this.totalRounds);
        int matches = 0;

        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            for (int opponentIndex = playerIndex + 1; opponentIndex < players.size(); opponentIndex++) {
                matches++;

                Player player = players.get(playerIndex);
                Player opponent = players.get(opponentIndex);

                player.resetAllMoves();
                opponent.resetAllMoves();

                for (int round = 0; round < this.totalRounds; round++) {
                    Strategy.Move playerMove = player.play(opponent);
                    Strategy.Move opponentMove = opponent.play(player);

                    int playerScore = getPlayer1Score(playerMove, opponentMove);
                    int opponentScore = getPlayer1Score(opponentMove, playerMove);

                    player.increaseScore(playerScore);
                    opponent.increaseScore(opponentScore);

                    System.out.printf("Round %d : %s (%s) vs Player %s (%s) : %d vs %d\n",
                            round, player, playerMove, opponent, opponentMove, playerScore, opponentScore);
                }
            }
        }

        System.out.printf("%d-round(s) Tournament ended with %d matches\n", this.totalRounds, matches);
        return matches;
    }

    private static int getPlayer1Score(Strategy.Move player1Move, Strategy.Move player2Move) {
        if (player1Move == Strategy.Move.DEFLECT && player2Move == Strategy.Move.DEFLECT) {
            return 1;
        }
        else if (player1Move == Strategy.Move.DEFLECT && player2Move == Strategy.Move.COOPERATE) {
            return 5;
        }
        else if (player1Move == Strategy.Move.COOPERATE && player2Move == Strategy.Move.DEFLECT) {
            return 0;
        }
        else {
            return 3;
        }
    }

    public Player getWinner() {
        System.out.println("\nScore board:");

        List<Player> topScorers = this.players
                .stream()
                .sorted(Comparator
                        .comparingInt(Player::getScore)
                        .reversed()
                        .thenComparingInt(Player::getPlayerId))
                .collect(Collectors.toList());

        topScorers.forEach(player -> System.out.printf("%s has score: %d\n", player, player.getScore()));

        return topScorers.get(0);
    }
}

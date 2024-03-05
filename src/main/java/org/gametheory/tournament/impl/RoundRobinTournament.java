package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.tournament.TournamentConfig;

import java.util.List;

public class RoundRobinTournament extends Tournament {
    public RoundRobinTournament(TournamentConfig tournamentConfig) {
        super(tournamentConfig);
    }

    @Override
    public int start() {
        return start(players);
    }

    final protected int start(List<Player> players) {
        List<Strategy> strategies = getDistinctStrategies(players);

        System.out.printf("Starting %s with %d round(s) and %d player(s)\n", this.getClass().getSimpleName(), totalRounds, players.size());
        System.out.printf("Players population: %d nice, %d not nice\n",
                players.stream().filter(player -> player.getStrategy().isNice()).count(),
                players.stream().filter(player -> !player.getStrategy().isNice()).count());
        System.out.printf("%d unique strategies: %s\n\n",
                strategies.size(),
                strategies.stream().map(Strategy::getName));

        int matches = 0;

        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            for (int opponentIndex = playerIndex + 1; opponentIndex < players.size(); opponentIndex++) {
                matches++;
                match(players.get(playerIndex), players.get(opponentIndex));
            }

            if (showMatches) {
                System.out.println();
            }
        }

        System.out.printf("%d-round(s) %s ended with %d matches from %d players\n", totalRounds, this.getClass().getSimpleName(), matches, players.size());

        return matches;
    }
}

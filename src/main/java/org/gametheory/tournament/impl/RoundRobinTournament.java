package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.tournament.TournamentConfig;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoundRobinTournament extends Tournament {
    public RoundRobinTournament(TournamentConfig tournamentConfig) {
        super(tournamentConfig);
    }

    @Override
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
                match(players.get(playerIndex), players.get(opponentIndex));
            }

            if (showMatches) {
                System.out.println();
            }
        }

        System.out.printf("%d-round(s) Tournament ended with %d matches from %d players\n", totalRounds, matches, players.size());
        return matches;
    }
}

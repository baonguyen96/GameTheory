package org.gametheory.tournament.impl;

import org.apache.commons.collections.ListUtils;
import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.tournament.TournamentConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OneVsOneTournament extends Tournament {
    private final Player playerAgainstOthers;
    private final List<Player> allPlayers;
    private final Map<Player, MatchResult> matchResults;

    public OneVsOneTournament(TournamentConfig tournamentConfig) {
        super(tournamentConfig);
        this.playerAgainstOthers = tournamentConfig.getPlayerAgainstOthers();
        this.allPlayers = ListUtils.union(players, Collections.singletonList(playerAgainstOthers));
        this.matchResults = new HashMap<>();
    }

    @Override
    public int start() {
        List<Strategy> uniqueStrategies = getUniqueStrategies(allPlayers);

        System.out.printf("Starting %s with %d round(s) for %s vs %d player(s)\n",
                this.getClass().getSimpleName(), totalRounds, playerAgainstOthers, players.size());
        System.out.printf("Players population: %d nice, %d not nice\n",
                allPlayers.stream().filter(player -> player.getStrategy().isNice()).count(),
                allPlayers.stream().filter(player -> !player.getStrategy().isNice()).count());
        System.out.printf("%d unique strategies: %s\n\n",
                uniqueStrategies.size(),
                uniqueStrategies.stream().map(Strategy::getName).collect(Collectors.toList()));

        for (Player player : players) {
            MatchResult matchResult = match(playerAgainstOthers, player);
            matchResults.put(player, matchResult);
            playerAgainstOthers.increaseScore(-1 * playerAgainstOthers.getScore());
        }

        System.out.printf("\n%d-round(s) %s ended for %s against %d other players\n",
                totalRounds, this.getClass().getSimpleName(), playerAgainstOthers, players.size());

        return players.size();
    }

    @Override
    public Player getWinner() {
        boolean neverLost = matchResults
                .entrySet()
                .stream()
                .noneMatch(resultAgainstPlayer -> resultAgainstPlayer.getValue() == MatchResult.Lose);
        Player winner = null;

        if (neverLost) {
            System.out.printf("\nCongrats! You (%s) won against all others!\n", playerAgainstOthers);
            winner = playerAgainstOthers;
        }
        else {
            List<Player> betterPlayers = matchResults
                    .entrySet()
                    .stream()
                    .filter(es -> es.getValue() == MatchResult.Lose)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            System.out.printf("\nBooo! You (%s) lost to %d/%d other players: %s\n",
                    playerAgainstOthers, betterPlayers.size(), players.size(), betterPlayers);
        }

        return winner;
    }
}

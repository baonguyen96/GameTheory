package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.tournament.TournamentConfig;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EliminationAndEvolutionTournament extends RoundRobinTournament {
    private final int cycles;
    private final int replacementCount;

    public EliminationAndEvolutionTournament(TournamentConfig tournamentConfig) {
        super(tournamentConfig);
        this.cycles = tournamentConfig.getCycle();
        this.replacementCount = this.players.size() * tournamentConfig.getReplacementPercentage() / 100;
    }

    @Override
    public int start() {
        int matches = 0;
        List<Player> currentPlayers = this.players;

        System.out.printf("Starting %s with %d cycle(s) and %d replacement(s) for %d player(s)\n",
                this.getClass().getSimpleName(), cycles, replacementCount, players.size());

        for (int cycle = 0; cycle < cycles; cycle++) {
            System.out.printf("\nEliminationAndEvolutionTournament cycle #%d\n\n", cycle + 1);

            if (cycle > 0) {
                currentPlayers = evolve(currentPlayers);
            }

            matches += super.start(currentPlayers);

            if (cycle < cycles - 1) {
                getWinner(currentPlayers);
            }
        }

        System.out.printf("%d-cycle(s)-with-%d-replacement(s) %s ended with %d matches\n\n",
                cycles, replacementCount, this.getClass().getSimpleName(), matches);

        showPopulationComparison(this.players, currentPlayers);
        showMostPopularStrategies(this.players, "before");
        showMostPopularStrategies(currentPlayers, "after");
        showEliminatedStrategies(this.players, currentPlayers);

        this.players = currentPlayers;

        return matches;
    }

    private void showPopulationComparison(List<Player> beforePlayers, List<Player> afterPlayers) {
        long nicePopulationBefore = beforePlayers.stream().filter(player -> player.getStrategy().isNice()).count();
        long notNicePopulationBefore = beforePlayers.stream().filter(player -> !player.getStrategy().isNice()).count();
        long nicePopulationAfter = afterPlayers.stream().filter(player -> player.getStrategy().isNice()).count();
        long notNicePopulationAfter = afterPlayers.stream().filter(player -> !player.getStrategy().isNice()).count();

        System.out.printf("Original population: %d nice, %d not nice\n", nicePopulationBefore, notNicePopulationBefore);
        System.out.printf("Evolved population: %d nice, %d not nice\n\n", nicePopulationAfter,notNicePopulationAfter);

        if (nicePopulationBefore > nicePopulationAfter) {
            System.out.println("Unfortunately, the world is getting worse :(");
        }
        else if (nicePopulationBefore == nicePopulationAfter) {
            System.out.println("Nothing has changed :|");
        }
        else {
            System.out.println("Fortunately, the world is now a better place :)");
        }
    }

    private void showMostPopularStrategies(List<Player> players, String when) {
        System.out.printf("\nMost popular strategies %s evolution:\n", when);

        Map<String, Boolean> strategies = players
                .stream()
                .map(Player::getStrategy)
                .distinct()
                .collect(Collectors.toMap(Strategy::getName, Strategy::isNice, (existing, replacement) -> existing));

        players
                .stream()
                .map(Player::getStrategy)
                .collect(Collectors.groupingBy(Strategy::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(es -> System.out.printf("%s (isNice: %s): %d\n", es.getKey(), strategies.get(es.getKey()), es.getValue()));
    }

    private void showEliminatedStrategies(List<Player> beforePlayers, List<Player> afterPlayers) {
        System.out.println("\nEliminated strategies:");

        List<String> strategiesAfter = afterPlayers
                .stream()
                .map(player -> player.getStrategy().getName())
                .collect(Collectors.toList());

        beforePlayers
                .stream()
                .map(Player::getStrategy)
                .filter(strategy -> !strategiesAfter.contains(strategy.getName()))
                .collect(Collectors.toMap(Strategy::getName, strategy -> strategy, (existing, replacement) -> existing))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(es -> System.out.printf("%s (isNice: %s)\n", es.getKey(), es.getValue().isNice()));
    }

    List<Player> clonePlayers(List<Player> currentPlayers) {
        return currentPlayers
                .stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    List<Player> evolve(List<Player> currentPlayers) {
        System.out.printf("Eliminating worst %d player(s) and replacing them with top %d player(s)\n", replacementCount, replacementCount);

        List<Player> rankedPlayers = getRankedPlayers(currentPlayers);
        List<Player> topPlayers = rankedPlayers.subList(0, replacementCount);
        List<Player> removedBottomPlayers = rankedPlayers.subList(0, rankedPlayers.size() - replacementCount);
        removedBottomPlayers.addAll(topPlayers);

        return clonePlayers(removedBottomPlayers);
    }
}

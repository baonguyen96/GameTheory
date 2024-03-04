package org.gametheory;

import org.gametheory.player.*;
import org.gametheory.tournament.*;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        int populationSize = getRandomNumber(200, 500);
        int rounds = getRandomNumber(50, 200);
        int proportionOfGoodness = getRandomNumber(1, 9);
        int cycles = getRandomNumber(2, 5);
        boolean showMatches = false;
        int replacementPercentage = 10;
//        List<Player> players = PlayerPopulation.getPopulationWithGoodAndBadPlayers(populationSize / proportionOfGoodness, populationSize / (10 - proportionOfGoodness));
        List<Player> players = PlayerPopulation.getMixedButUniquePlayersTwice();

        TournamentConfig tournamentConfig = new TournamentConfig(players, cycles, rounds, showMatches, replacementPercentage);
        Tournament tournament = new EliminationAndEvolutionTournament(tournamentConfig);
        tournament.start();

        Player winner = tournament.getWinner();
        System.out.printf("\nWinner is %s\n", winner);
    }

    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
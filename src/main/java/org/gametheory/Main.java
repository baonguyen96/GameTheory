package org.gametheory;

import org.gametheory.player.*;
import org.gametheory.tournament.*;
import org.gametheory.tournament.impl.Tournament;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        int proportionOfGoodness = getRandomNumber(1, 9);

        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(PlayerPopulation.getMixedButUniquePlayers())
                .withCycle(getRandomNumber(2, 5))
                .withRound(getRandomNumber(50, 200))
                .withShowMatches(true)
                .withReplacementPercentage(10)
                .build();

        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);
        tournament.start();

        Player winner = tournament.getWinner();
        System.out.printf("\nWinner is %s\n", winner);
    }

    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
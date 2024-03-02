package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.player.PlayerPopulation;
import org.gametheory.tournament.Tournament;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int populationSize = getRandomNumber(500, 1000);
        int rounds = getRandomNumber(100, 300);
        boolean showMatches = false;

        List<Player> players = PlayerPopulation.getPopulationWithGoodAndBadPlayers(1, populationSize - 1);
        Tournament tournament = new Tournament(rounds, players, showMatches);
        tournament.start();

        Player winner = tournament.getWinner();
        System.out.printf("\nWinner is %s\n", winner);
    }

    private static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
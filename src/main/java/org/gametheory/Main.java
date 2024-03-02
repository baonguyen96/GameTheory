package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.player.PlayerPopulation;
import org.gametheory.tournament.Tournament;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int populationSize = random.nextInt(250);
        int rounds = random.nextInt(200);

        List<Player> players = PlayerPopulation.getBigPopulationPlayers(populationSize);
        Tournament tournament = new Tournament(rounds, players);
        tournament.start();

        Player winner = tournament.getWinner();
        System.out.println("\nWinner is " + winner);
    }
}
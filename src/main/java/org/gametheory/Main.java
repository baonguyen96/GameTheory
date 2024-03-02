package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.tournament.Tournament;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Player> players = PlayerPopulation.getMixedButUniquePlayers();

        int tournamentRounds = new Random().nextInt(1000);

        Tournament tournament = new Tournament(tournamentRounds, players);

        tournament.start();

        Player winner = tournament.getWinner();

        System.out.println("\nWinner is " + winner);
    }
}
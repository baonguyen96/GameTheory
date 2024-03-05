package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.player.PlayerPopulation;
import org.gametheory.strategy.impl.*;
import org.gametheory.tournament.TournamentConfig;
import org.gametheory.tournament.TournamentFactory;
import org.gametheory.tournament.impl.Tournament;
import org.gametheory.utils.Utility;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        int proportionOfGoodness = Utility.getRandomNumber(1, 9);
        List<Player> players = PlayerPopulation.getMixedButUniquePlayers();
        Player playerAgainstTheRest = Utility.firstOrDefault(players, player -> player.getStrategy().is(new AlwaysDefect()), null);

        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withCycle(Utility.getRandomNumber(1, 1))
                .withRound(Utility.getRandomNumber(1, 5))
                .withShowMatches(true)
                .withReplacementPercentage(10)
                .withPlayerIdToAgainstTheRest(playerAgainstTheRest == null ? -1 : playerAgainstTheRest.getPlayerId())
                .build();

        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);
        tournament.start();

        Player winner = tournament.getWinner();
        System.out.printf("\nWinner is %s\n", winner);
    }
}
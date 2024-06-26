package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.player.PlayerPopulation;
import org.gametheory.tournament.TournamentConfig;
import org.gametheory.tournament.TournamentFactory;
import org.gametheory.tournament.impl.Tournament;
import org.gametheory.utils.Utility;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        int headCount = Utility.getRandomNumber(100, 1000);
        int goodPercentage = Utility.getRandomNumber(2, 5);
        List<Player> players = PlayerPopulation.getPopulationWithGoodAndBadPlayers(headCount / 10 * goodPercentage, headCount / 10 * (10 - goodPercentage));
        Player playerAgainstTheRest = Utility.firstOrDefault(players, player -> player.getPlayerId() < 0, null);

        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withCycle(Utility.getRandomNumber(1, 10))
                .withRound(Utility.getRandomNumber(1, 10))
                .withShowMatches(false)
                .withReplacementPercentage(Utility.getRandomNumber(5, 20))
                .withPlayerIdToAgainstTheRest(playerAgainstTheRest == null ? TournamentConfig.Builder.NOBODY : playerAgainstTheRest.getPlayerId())
                .build();

        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);
        tournament.start();

        Player winner = tournament.getWinner();
        System.out.printf("\nWinner is %s\n", winner);
    }
}
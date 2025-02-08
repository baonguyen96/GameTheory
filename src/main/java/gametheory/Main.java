package gametheory;

import gametheory.player.Player;
import gametheory.player.PlayerPopulation;
import gametheory.tournament.TournamentConfig;
import gametheory.tournament.TournamentFactory;
import gametheory.tournament.impl.Tournament;
import gametheory.utils.Utility;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PlayerPopulation playerPopulation = new PlayerPopulation(false);
        int headCount = Utility.getRandomNumber(50, 200);
        int goodPercentage = Utility.getRandomNumber(5, 5);
        List<Player> players = playerPopulation.getPopulationWithGoodAndBadPlayers(headCount / 10 * goodPercentage, headCount / 10 * (10 - goodPercentage));
        Player playerAgainstTheRest = Utility.firstOrDefault(players, player -> player.getPlayerId() < 0, null);

        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withCycle(Utility.getRandomNumber(1, 1))
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
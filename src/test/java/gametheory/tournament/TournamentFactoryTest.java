package gametheory.tournament;

import gametheory.player.Player;
import gametheory.strategy.impl.AlwaysCooperate;
import gametheory.strategy.impl.AlwaysDefect;
import gametheory.tournament.TournamentConfig;
import gametheory.tournament.TournamentFactory;
import gametheory.tournament.impl.EliminationAndEvolutionTournament;
import gametheory.tournament.impl.OneVsOneTournament;
import gametheory.tournament.impl.RoundRobinTournament;
import gametheory.tournament.impl.Tournament;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TournamentFactoryTest {
    @Test
    public void getTournament_roundRobin() {
        TournamentConfig tournamentConfig = TournamentConfig.builder().build();
        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);

        assertTrue(tournament instanceof RoundRobinTournament);
    }

    @Test
    public void getTournament_elimination() {
        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withCycle(2)
                .withPlayers(Collections.emptyList())
                .build();
        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);

        assertTrue(tournament instanceof EliminationAndEvolutionTournament);
    }

    @Test
    public void getTournament_oneVsOne() {
        Player player = new Player(new AlwaysCooperate());
        List<Player> players = Arrays.asList(player, new Player(new AlwaysDefect()));
        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withPlayerIdToAgainstTheRest(player.getPlayerId())
                .build();
        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);

        assertTrue(tournament instanceof OneVsOneTournament);
    }
}
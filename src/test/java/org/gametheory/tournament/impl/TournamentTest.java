package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.AlwaysCooperate;
import org.gametheory.strategy.impl.AlwaysDefect;
import org.gametheory.strategy.impl.Simpleton;
import org.gametheory.tournament.TournamentConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TournamentTest {
    private Tournament tournament;
    private List<Player> players;

    @Before
    public void setUp() {
        players = Arrays.asList(
                new Player(new AlwaysCooperate()),
                new Player(new AlwaysCooperate()),
                new Player(new AlwaysDefect())
        );

        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withRound(1)
                .build();

        tournament = new RoundRobinTournament(tournamentConfig);
    }

    @Test
    public void start() {
        int matches  = tournament.start();
        assertEquals(3, matches);
    }

    @Test
    public void getWinner() {
        tournament.start();
        Player winner = tournament.getWinner();
        assertTrue(winner.getStrategy().ofType(AlwaysDefect.class));
    }

    @Test
    public void getUniqueStrategies() {
        List<Strategy> uniqueStrategies = Tournament.getDistinctStrategies(players);
        assertEquals(2, uniqueStrategies.size());
        assertTrue(uniqueStrategies.stream().anyMatch(strategy -> strategy.ofType(AlwaysCooperate.class)));
        assertTrue(uniqueStrategies.stream().anyMatch(strategy -> strategy.ofType(AlwaysDefect.class)));
    }

    @Test
    public void getPlayer1Score() {
        assertEquals(1, Tournament.getPlayer1Score(Strategy.Move.DEFECT, Strategy.Move.DEFECT));
        assertEquals(5, Tournament.getPlayer1Score(Strategy.Move.DEFECT, Strategy.Move.COOPERATE));
        assertEquals(0, Tournament.getPlayer1Score(Strategy.Move.COOPERATE, Strategy.Move.DEFECT));
        assertEquals(3, Tournament.getPlayer1Score(Strategy.Move.COOPERATE, Strategy.Move.COOPERATE));
    }

    @Test
    public void getRankedPlayers() {
        Player player1 = new Player(new AlwaysCooperate());
        Player player2 = new Player(new AlwaysDefect());
        Player player3 = new Player(new Simpleton());
        player1.increaseScore(1);
        player2.increaseScore(2);
        player3.increaseScore(2);

        List<Player> ranked = Tournament.getRankedPlayers(Arrays.asList(player1, player2, player3));

        assertTrue(ranked.get(0).getStrategy().ofType(Simpleton.class));
        assertTrue(ranked.get(1).getStrategy().ofType(AlwaysDefect.class));
        assertTrue(ranked.get(2).getStrategy().ofType(AlwaysCooperate.class));
    }
}
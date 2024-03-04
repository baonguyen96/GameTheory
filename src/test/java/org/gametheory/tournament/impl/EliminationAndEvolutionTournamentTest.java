package org.gametheory.tournament.impl;

import org.gametheory.player.Player;
import org.gametheory.strategy.impl.AlwaysCooperate;
import org.gametheory.strategy.impl.AlwaysDefect;
import org.gametheory.tournament.TournamentConfig;
import org.gametheory.tournament.impl.EliminationAndEvolutionTournament;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class EliminationAndEvolutionTournamentTest {
    private EliminationAndEvolutionTournament tournament;
    private List<Player> players;

    @Before
    public void setUp() throws Exception {
        Player coopPlayer1 = new Player(new AlwaysCooperate());
        Player coopPlayer2 = new Player(new AlwaysCooperate());
        Player defectPlayer1 = new Player(new AlwaysDefect());
        Player defectPlayer2 = new Player(new AlwaysDefect());

        coopPlayer1.increaseScore(1);
        coopPlayer2.increaseScore(3);
        defectPlayer1.increaseScore(3);
        defectPlayer2.increaseScore(5);

        players = Arrays.asList(coopPlayer1, coopPlayer2, defectPlayer1, defectPlayer2);

        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withCycle(2)
                .withRound(1)
                .withReplacementPercentage(25)
                .build();
        tournament = new EliminationAndEvolutionTournament(tournamentConfig);
    }

    @Test
    public void start() {
        int matches  = tournament.start();
        assertEquals(12, matches);
    }

    @Test
    public void clonePlayers() {
        Player player = new Player(new AlwaysCooperate());
        List<Player> clones = tournament.clonePlayers(Collections.singletonList(player));

        assertEquals(1, clones.size());
        assertNotEquals(player.getPlayerId(), clones.get(0).getPlayerId());
        assertTrue(player.getStrategy() instanceof AlwaysCooperate);
    }

    @Test
    public void evolve() {
        List<Player> evolvedPlayers = tournament.evolve(players);

        assertEquals(players.size(), evolvedPlayers.size());
        assertEquals(1L, evolvedPlayers.stream().filter(player -> player.getStrategy().equals(new AlwaysCooperate())).count());
        assertEquals(3L, evolvedPlayers.stream().filter(player -> player.getStrategy().equals(new AlwaysDefect())).count());
    }
}
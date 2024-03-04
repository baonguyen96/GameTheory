package org.gametheory.tournament;

import org.gametheory.tournament.impl.EliminationAndEvolutionTournament;
import org.gametheory.tournament.impl.RoundRobinTournament;
import org.gametheory.tournament.impl.Tournament;
import org.junit.Test;

import java.util.Collections;

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
        TournamentConfig tournamentConfig = TournamentConfig.builder().withCycle(2).withPlayers(Collections.emptyList()).build();
        Tournament tournament = TournamentFactory.getTournament(tournamentConfig);
        assertTrue(tournament instanceof EliminationAndEvolutionTournament);
    }
}
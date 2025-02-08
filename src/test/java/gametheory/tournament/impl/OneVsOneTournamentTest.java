package gametheory.tournament.impl;

import gametheory.player.Player;
import gametheory.strategy.impl.AlwaysCooperate;
import gametheory.strategy.impl.AlwaysDefect;
import gametheory.strategy.impl.DefectAtInterval;
import gametheory.tournament.TournamentConfig;
import gametheory.tournament.impl.OneVsOneTournament;
import gametheory.tournament.impl.Tournament;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OneVsOneTournamentTest {
    private Tournament tournament;

    @Before
    public void setUp() throws Exception {
        Player coopPlayer = new Player(new AlwaysCooperate());
        Player defectPlayer = new Player(new AlwaysDefect());
        Player defectAtIntervalPlayer = new Player(new DefectAtInterval());
        List<Player> players = Arrays.asList(coopPlayer, defectPlayer, defectAtIntervalPlayer);
        TournamentConfig tournamentConfig = TournamentConfig
                .builder()
                .withPlayers(players)
                .withPlayerIdToAgainstTheRest(coopPlayer.getPlayerId())
                .build();

        tournament = new OneVsOneTournament(tournamentConfig);
    }

    @Test
    public void start() {
        int matches = tournament.start();
        assertEquals(2, matches);
    }

    @Test
    public void getWinner() {
        tournament.start();
        assertNull(tournament.getWinner());
    }
}
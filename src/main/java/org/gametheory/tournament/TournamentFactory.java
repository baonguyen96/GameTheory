package org.gametheory.tournament;

import org.gametheory.tournament.impl.EliminationAndEvolutionTournament;
import org.gametheory.tournament.impl.OneVsOneTournament;
import org.gametheory.tournament.impl.RoundRobinTournament;
import org.gametheory.tournament.impl.Tournament;

public class TournamentFactory {
    public static Tournament getTournament(TournamentConfig tournamentConfig) {
        Tournament tournament;

        if (tournamentConfig.getCycle() > 1) {
            tournament = new EliminationAndEvolutionTournament(tournamentConfig);
        }
        else if (tournamentConfig.getPlayerAgainstOthers() != null) {
            tournament = new OneVsOneTournament(tournamentConfig);
        }
        else {
            tournament = new RoundRobinTournament(tournamentConfig);
        }

        return tournament;
    }
}

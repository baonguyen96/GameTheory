package gametheory.tournament;

import gametheory.tournament.impl.EliminationAndEvolutionTournament;
import gametheory.tournament.impl.OneVsOneTournament;
import gametheory.tournament.impl.RoundRobinTournament;
import gametheory.tournament.impl.Tournament;

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

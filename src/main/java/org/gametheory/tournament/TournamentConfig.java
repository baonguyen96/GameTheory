package org.gametheory.tournament;

import org.gametheory.player.Player;

import java.util.List;

public class TournamentConfig {
    private final List<Player> players;
    private final int cycle;
    private final int roundPerCycle;
    private final boolean showMatches;
    private final int replacementPercentage;

    public TournamentConfig(List<Player> players, int cycle, int roundPerCycle, boolean showMatches, int replacementPercentage) {
        this.players = players;
        this.cycle = cycle;
        this.roundPerCycle = roundPerCycle;
        this.showMatches = showMatches;
        this.replacementPercentage = replacementPercentage;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCycle() {
        return cycle;
    }

    public int getRoundPerCycle() {
        return roundPerCycle;
    }

    public boolean isShowMatches() {
        return showMatches;
    }

    public int getReplacementPercentage() {
        return replacementPercentage;
    }
}

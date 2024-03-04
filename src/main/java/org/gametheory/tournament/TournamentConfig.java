package org.gametheory.tournament;

import org.gametheory.player.Player;

import java.util.List;

public class TournamentConfig {
    public static class Builder {
        private List<Player> players;
        private int cycle;
        private int round;
        private boolean showMatches;
        private int replacementPercentage;

        public TournamentConfig build() {
            return new TournamentConfig(players, cycle, round, showMatches, replacementPercentage);
        }

        public Builder withPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder withCycle(int cycle) {
            this.cycle = cycle;
            return this;
        }

        public Builder withRound(int roundPerCycle) {
            this.round = roundPerCycle;
            return this;
        }

        public Builder withShowMatches(boolean showMatches) {
            this.showMatches = showMatches;
            return this;
        }

        public Builder withReplacementPercentage(int replacementPercentage) {
            this.replacementPercentage = replacementPercentage;
            return this;
        }
    }

    private final List<Player> players;
    private final int cycle;
    private final int round;
    private final boolean showMatches;
    private final int replacementPercentage;

    private TournamentConfig(List<Player> players, int cycle, int round, boolean showMatches, int replacementPercentage) {
        this.players = players;
        this.cycle = Math.max(1, cycle);
        this.round = Math.max(1, round);
        this.showMatches = showMatches;
        this.replacementPercentage = Math.max(0, replacementPercentage);
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCycle() {
        return cycle;
    }

    public int getRound() {
        return round;
    }

    public boolean isShowMatches() {
        return showMatches;
    }

    public int getReplacementPercentage() {
        return replacementPercentage;
    }
}

package org.gametheory.tournament;

import org.gametheory.player.Player;

import java.util.List;
import java.util.stream.Collectors;

public class TournamentConfig {
    public static class Builder {
        private List<Player> players;
        private int cycle;
        private int round;
        private boolean showMatches;
        private int replacementPercentage;
        private int playerIdToAgainstTheRest = -1;

        public TournamentConfig build() {
            Player player = null;
            List<Player> finalPlayers = players;

            if (playerIdToAgainstTheRest >= 0) {
                player = players.stream().filter(p -> p.getPlayerId() == playerIdToAgainstTheRest).collect(Collectors.toList()).get(0);
                finalPlayers = players.stream().filter(p -> p.getPlayerId() != playerIdToAgainstTheRest).collect(Collectors.toList());
            }

            return new TournamentConfig(
                    finalPlayers,
                    player,
                    Math.max(1, cycle),
                    Math.max(1, round),
                    showMatches,
                    Math.max(0, replacementPercentage)
            );
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

        public Builder withPlayerIdToAgainstTheRest(int playerIdToAgainstTheRest) {
            this.playerIdToAgainstTheRest = playerIdToAgainstTheRest;
            return this;
        }
    }

    private final List<Player> players;
    private final Player playerAgainstOthers;
    private final int cycle;
    private final int round;
    private final boolean showMatches;
    private final int replacementPercentage;

    private TournamentConfig(List<Player> players, Player playerAgainstOthers, int cycle, int round, boolean showMatches, int replacementPercentage) {
        this.players = players;
        this.playerAgainstOthers = playerAgainstOthers;
        this.cycle = cycle;
        this.round = round;
        this.showMatches = showMatches;
        this.replacementPercentage = replacementPercentage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerAgainstOthers() {
        return playerAgainstOthers;
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

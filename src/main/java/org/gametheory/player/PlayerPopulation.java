package org.gametheory.player;

import org.apache.commons.collections.ListUtils;
import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.*;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerPopulation {
    public static List<Player> getAlwaysCooperateAndDeflectPlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDefectStrategy())
        );
    }

    public static List<Player> getSameTypeOfPlayers() {
        return Arrays.asList(
                new Player(new DefectRandomlyAndKeepDefectIfNotRetaliatedStrategy()),
                new Player(new DefectRandomlyAndKeepDefectIfNotRetaliatedStrategy())
        );
    }

    public static List<Player> getMixedButUniquePlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDefectStrategy()),
                new Player(new CopyOpponentLastMoveStrategy()),
                new Player(new DefectAtIntervalStrategy()),
                new Player(new DefectFirstAndKeepDefectIfNotRetaliatedStrategy()),
                new Player(new DefectRandomlyAndKeepDefectIfNotRetaliatedStrategy()),
                new Player(new KeepDefectOnceAttackedStrategy()),
                new Player(new OnlyRetaliateIfAttackedButAttackOnFirstMoveStrategy()),
                new Player(new OnlyRetaliateIfAttackedConsecutivelyStrategy()),
                new Player(new OnlyRetaliateIfAttackedStrategy()),
                new Player(new RandomMoveStrategy())
        );
    }

    public static List<Player> getMixedButUniquePlayersTwice() {
        List<Player> players = getMixedButUniquePlayers();
        List<Player> clones = getMixedButUniquePlayers();
        return ListUtils.union(players, clones);
    }

    public static List<Player> getBigPopulationPlayers(int size) {
        List<Strategy> distinctStrategies = getMixedButUniquePlayers().stream().map(Player::getStrategy).collect(Collectors.toList());
        List<Player> players = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            players.add(new Player(distinctStrategies.get(random.nextInt(distinctStrategies.size()))));
        }

        return  players;
    }

    public static List<Player> getOnlyPlayersWhoCooperateFirst() {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, true);
    }

    public static List<Player> getOnlyPlayersWhoDefectFirst() {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, false);
    }

    public static List<Player> getIsolatedGoodPlayerWithinAbusingPopulation(int size) {
        List<Player> allPlayers = getBigPopulationPlayers(size - 1);
        List<Player> population = getPlayersWithStrategyCharacteristic(allPlayers, false);
        Player goodPlayer = getPlayersWithStrategyCharacteristic(allPlayers, true).get(0);
        population.add(goodPlayer);
        return population;
    }

    public static List<Player> getIsolatedAbusivePlayerWithinGoodPopulation(int size) {
        List<Player> allPlayers = getBigPopulationPlayers(size - 1);
        List<Player> population = getPlayersWithStrategyCharacteristic(allPlayers, true);
        Player goodPlayer = getPlayersWithStrategyCharacteristic(allPlayers, false).get(0);
        population.add(goodPlayer);
        return population;
    }

    private static List<Player> getPlayersWithStrategyCharacteristic(List<Player> players, boolean isNice) {
        return players
                .stream()
                .filter(player -> player.getStrategy().isNice() == isNice)
                .collect(Collectors.toList());
    }
}

package org.gametheory.player;

import org.apache.commons.collections.ListUtils;
import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static List<Player> getCustomPopulation() {
        return Arrays.asList(
                new Player(new AlternateMoveCooperateFirst()),
                new Player(new AlternateMoveDefectFirst()),
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDefectStrategy())
        );
    }

    public static List<Player> getMixedButUniquePlayers() {
        return Arrays.asList(
                new Player(new AlternateMoveCooperateFirst()),
                new Player(new AlternateMoveDefectFirst()),
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDefectStrategy()),
                new Player(new CopyOpponentLastMoveStrategy()),
                new Player(new DefectAtIntervalStrategy()),
                new Player(new DefectFirstAndKeepDefectIfNotRetaliatedStrategy()),
                new Player(new DefectRandomlyAndKeepDefectIfNotRetaliatedStrategy()),
                new Player(new KeepDefectOnceBeingAttackedStrategy()),
                new Player(new NextMoveBasedOnForecastFromHistoricalStrategy()),
                new Player(new OnlyRetaliateIfBeingBeingAttackedButAttackOnFirstMoveStrategy()),
                new Player(new OnlyRetaliateIfBeingBeingAttackedConsecutivelyStrategy()),
                new Player(new OnlyRetaliateIfBeingAttackedStrategy()),
                new Player(new RandomMoveStrategy())
        );
    }

    public static List<Player> getMixedButUniquePlayersTwice() {
        List<Player> players = getMixedButUniquePlayers();
        List<Player> clones = getMixedButUniquePlayers();
        return ListUtils.union(players, clones);
    }

    public static List<Player> getBigPopulationPlayers(int size) {
        return getBigPopulationPlayers(size, null);
    }

    private static List<Player> getBigPopulationPlayers(int size, Boolean filterStrategyCharacteristic) {
        Stream<Strategy> distinctStrategies = getMixedButUniquePlayers().stream().map(Player::getStrategy);

        if (filterStrategyCharacteristic != null) {
            distinctStrategies = distinctStrategies.filter(strategy -> strategy.isNice() == filterStrategyCharacteristic);
        }

        List<Strategy> strategies = distinctStrategies.collect(Collectors.toList());
        Collections.shuffle(strategies);
        List<Player> players = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            players.add(new Player(strategies.get(random.nextInt(strategies.size()))));
        }

        return players;
    }

    public static List<Player> getOnlyPlayersWhoCooperateFirst() {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, true);
    }

    public static List<Player> getOnlyPlayersWhoDefectFirst() {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, false);
    }

    private static List<Player> getPlayersWithStrategyCharacteristic(List<Player> players, boolean isNice) {
        return players
                .stream()
                .filter(player -> player.getStrategy().isNice() == isNice)
                .collect(Collectors.toList());
    }

    public static List<Player> getPopulationWithGoodAndBadPlayers(int goodPlayerCount, int badPlayerCount) {
        List<Player> goodPlayers = getBigPopulationPlayers(goodPlayerCount, true);
        List<Player> badPlayers = getBigPopulationPlayers(badPlayerCount, false);
        return ListUtils.union(goodPlayers, badPlayers);
    }
}

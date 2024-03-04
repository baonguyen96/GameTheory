package org.gametheory.player;

import org.apache.commons.collections.ListUtils;
import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.*;
import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerPopulation {
    public static List<Player> getAlwaysCooperateAndDeflectPlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperate()),
                new Player(new AlwaysDefect())
        );
    }

    public static List<Player> getSameTypeOfPlayers() {
        return Arrays.asList(
                new Player(new DefectRandomlyAndKeepDefectIfNotRetaliated()),
                new Player(new DefectRandomlyAndKeepDefectIfNotRetaliated())
        );
    }

    public static List<Player> getCustomPopulation() {
        return Arrays.asList(
                new Player(new AlternateMoveCooperateFirst()),
                new Player(new AlternateMoveDefectFirst()),
                new Player(new AlwaysCooperate()),
                new Player(new AlwaysDefect())
        );
    }

    public static List<Player> getMixedButUniquePlayers() throws Exception {
        Reflections reflections = new Reflections("org.gametheory.strategy.impl");
        Set<Class<?>> strategies = new HashSet<>(reflections.getSubTypesOf(Strategy.class));
        List<Player> players = new LinkedList<>();

        for (Class<?> strategy : strategies) {
            players.add(new Player((Strategy) strategy.newInstance()));
        }

        return players;
    }

    public static List<Player> getMixedButUniquePlayersTwice() throws Exception {
        List<Player> players = getMixedButUniquePlayers();
        List<Player> clones = getMixedButUniquePlayers();
        return ListUtils.union(players, clones);
    }

    public static List<Player> getBigPopulationPlayers(int size) throws Exception {
        return getBigPopulationPlayers(size, null);
    }

    private static List<Player> getBigPopulationPlayers(int size, Boolean filterStrategyCharacteristic) throws Exception {
        List<Strategy> strategies = getMixedButUniquePlayers()
                .stream()
                .map(Player::getStrategy)
                .distinct()
                .filter(strategy -> filterStrategyCharacteristic == null
                        ? (strategy.isNice() || !strategy.isNice())
                        : (strategy.isNice() == filterStrategyCharacteristic))
                .collect(Collectors.toList());

        Collections.shuffle(strategies);
        List<Player> players = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            players.add(new Player(strategies.get(random.nextInt(strategies.size()))));
        }

        return players;
    }

    public static List<Player> getOnlyPlayersWhoCooperateFirst() throws Exception {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, true);
    }

    public static List<Player> getOnlyPlayersWhoDefectFirst() throws Exception {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, false);
    }

    private static List<Player> getPlayersWithStrategyCharacteristic(List<Player> players, boolean isNice) {
        return players
                .stream()
                .filter(player -> player.getStrategy().isNice() == isNice)
                .collect(Collectors.toList());
    }

    public static List<Player> getPopulationWithGoodAndBadPlayers(int goodPlayerCount, int badPlayerCount) throws Exception {
        List<Player> goodPlayers = getBigPopulationPlayers(goodPlayerCount, true);
        List<Player> badPlayers = getBigPopulationPlayers(badPlayerCount, false);
        return ListUtils.union(goodPlayers, badPlayers);
    }
}

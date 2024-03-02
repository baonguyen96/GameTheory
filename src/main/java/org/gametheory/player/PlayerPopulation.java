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

    public static List<Player> getMixedButUniquePlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDefectStrategy()),
                new Player(new CopyOpponentLastMoveStrategy()),
                new Player(new DefectAtIntervalStrategy()),
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

    public static List<Player> getOnlyPlayersWhoDoNotDefectFirst() {
        return getMixedButUniquePlayers()
                .stream()
                .filter(player -> player.getStrategy().makeFirstMove() != Strategy.Move.DEFECT)
                .collect(Collectors.toList());
    }
}

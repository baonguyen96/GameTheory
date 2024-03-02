package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PlayerPopulation {
    public static List<Player> getCooperateAndDeflectPlayers() {
        return List.of(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDeflectStrategy())
        );
    }

    public static List<Player> getMixedButUniquePlayers() {
        return List.of(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDeflectStrategy()),
                new Player(new OnlyRetaliateIfAttackedStrategy()),
                new Player(new OnlyRetaliateIfAttackedButAttackOnFirstMoveStrategy()),
                new Player(new OnlyRetaliateIfAttackedConsecutivelyStrategy()),
                new Player(new RandomMoveStrategy())
        );
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

    public static List<Player> getOnlyNicePlayers() {
        return List.of(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new OnlyRetaliateIfAttackedStrategy()),
                new Player(new OnlyRetaliateIfAttackedConsecutivelyStrategy())
        );
    }
}

package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.strategy.impl.*;

import java.util.List;

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

    public static List<Player> getOnlyNicePlayers() {
        return List.of(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new OnlyRetaliateIfAttackedStrategy()),
                new Player(new OnlyRetaliateIfAttackedConsecutivelyStrategy())
        );
    }
}

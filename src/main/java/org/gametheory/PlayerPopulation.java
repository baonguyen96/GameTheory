package org.gametheory;

import org.gametheory.player.Player;
import org.gametheory.strategy.impl.*;

import java.util.Arrays;
import java.util.List;

public class PlayerPopulation {
    public static List<Player> getMixedButUniquePlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new AlwaysDeflectStrategy()),
                new Player(new OnlyRetaliateIfAttackedStrategy()),
                new Player(new OnlyRetaliateIfAttackedButAttackOnFirstMoveStrategy()),
                new Player(new OnlyRetaliateIfAttackedConsecutivelyStrategy()),
                new Player(new RandomMoveStrategy())
        );
    }

    public static List<Player> getOnlyNicePlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperateStrategy()),
                new Player(new OnlyRetaliateIfAttackedStrategy()),
                new Player(new OnlyRetaliateIfAttackedConsecutivelyStrategy())
        );
    }
}

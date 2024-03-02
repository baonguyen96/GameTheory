package org.gametheory.player;

import org.gametheory.player.Player;
import org.gametheory.player.PlayerPopulation;
import org.gametheory.strategy.impl.AlwaysCooperateStrategy;
import org.gametheory.strategy.impl.AlwaysDeflectStrategy;
import org.gametheory.strategy.impl.OnlyRetaliateIfAttackedConsecutivelyStrategy;
import org.gametheory.strategy.impl.OnlyRetaliateIfAttackedStrategy;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerPopulationTest {
    @Test
    public void getCooperateAndDeflectPlayers() {
        List<Player> players = PlayerPopulation.getCooperateAndDeflectPlayers();
        assertTrue(players.get(0).getStrategy() instanceof AlwaysCooperateStrategy);
        assertTrue(players.get(1).getStrategy() instanceof AlwaysDeflectStrategy);
    }

    @Test
    public void getMixedButUniquePlayers() {
        assertFalse(PlayerPopulation.getMixedButUniquePlayers().isEmpty());
    }

    @Test
    public void getOnlyNicePlayers() {
        List<Player> players = PlayerPopulation.getOnlyNicePlayers();
        assertTrue(players.get(0).getStrategy() instanceof AlwaysCooperateStrategy);
        assertTrue(players.get(1).getStrategy() instanceof OnlyRetaliateIfAttackedStrategy);
        assertTrue(players.get(2).getStrategy() instanceof OnlyRetaliateIfAttackedConsecutivelyStrategy);
    }

    @Test
    public void getBigPopulationPlayers() {
        assertEquals(10, PlayerPopulation.getBigPopulationPlayers(10).size());
    }
}

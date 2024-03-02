package org.gametheory.player;

import org.gametheory.strategy.Strategy;
import org.gametheory.strategy.impl.AlwaysCooperateStrategy;
import org.gametheory.strategy.impl.AlwaysDefectStrategy;
import org.gametheory.strategy.impl.OnlyRetaliateIfAttackedConsecutivelyStrategy;
import org.gametheory.strategy.impl.OnlyRetaliateIfAttackedStrategy;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerPopulationTest {
    @Test
    public void getCooperateAndDeflectPlayers() {
        List<Player> players = PlayerPopulation.getAlwaysCooperateAndDeflectPlayers();
        assertTrue(players.get(0).getStrategy() instanceof AlwaysCooperateStrategy);
        assertTrue(players.get(1).getStrategy() instanceof AlwaysDefectStrategy);
    }

    @Test
    public void getMixedButUniquePlayers() {
        assertFalse(PlayerPopulation.getMixedButUniquePlayers().isEmpty());
    }

    @Test
    public void getOnlyNicePlayers() {
        List<Player> players = PlayerPopulation.getOnlyPlayersWhoDoNotDefectFirst();
        assertFalse(players.stream().allMatch(player -> player.getStrategy().makeFirstMove() == Strategy.Move.DEFECT));
    }

    @Test
    public void getBigPopulationPlayers() {
        assertEquals(10, PlayerPopulation.getBigPopulationPlayers(10).size());
    }

    @Test
    public void getMixedButUniquePlayersTwice() {
        assertEquals(PlayerPopulation.getMixedButUniquePlayers().size() * 2, PlayerPopulation.getMixedButUniquePlayersTwice().size());
    }
}

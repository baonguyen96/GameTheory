package org.gametheory.player;

import org.gametheory.strategy.impl.AlwaysCooperateStrategy;
import org.gametheory.strategy.impl.AlwaysDefectStrategy;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerPopulationTest {
    @Test
    public void getAlwaysCooperateAndDeflectPlayers() {
        List<Player> players = PlayerPopulation.getAlwaysCooperateAndDeflectPlayers();
        assertTrue(players.get(0).getStrategy() instanceof AlwaysCooperateStrategy);
        assertTrue(players.get(1).getStrategy() instanceof AlwaysDefectStrategy);
    }

    @Test
    public void getMixedButUniquePlayers() throws Exception {
        List<Player> players = PlayerPopulation.getMixedButUniquePlayers();
        System.out.println(players);
        assertFalse(players.isEmpty());
    }

    @Test
    public void getOnlyPlayersWhoCooperateFirst() throws Exception {
        List<Player> players = PlayerPopulation.getOnlyPlayersWhoCooperateFirst();
        assertTrue(players.stream().allMatch(player -> player.getStrategy().isNice()));
    }

    @Test
    public void getOnlyPlayersWhoDefectFirst() throws Exception {
        List<Player> players = PlayerPopulation.getOnlyPlayersWhoDefectFirst();
        assertTrue(players.stream().noneMatch(player -> player.getStrategy().isNice()));
    }

    @Test
    public void getBigPopulationPlayers() throws Exception {
        assertEquals(10, PlayerPopulation.getBigPopulationPlayers(10).size());
    }

    @Test
    public void getMixedButUniquePlayersTwice() throws Exception {
        assertEquals(PlayerPopulation.getMixedButUniquePlayers().size() * 2, PlayerPopulation.getMixedButUniquePlayersTwice().size());
    }

    @Test
    public void getSameTypeOfPlayers() {
        List<Player> players = PlayerPopulation.getSameTypeOfPlayers();
        assertTrue(players.size() > 1);
        assertEquals(1L, players.stream().map(player -> player.getStrategy().getClass().getSimpleName()).distinct().count());
    }

    @Test
    public void getCustomPopulation() {
        assertFalse(PlayerPopulation.getCustomPopulation().isEmpty());
    }

    @Test
    public void getPopulationWithGoodAndBadPlayers() throws Exception {
        List<Player> players = PlayerPopulation.getPopulationWithGoodAndBadPlayers(2, 8);
        assertEquals(2L, players.stream().filter(player -> player.getStrategy().isNice()).count());
        assertEquals(8L, players.stream().filter(player -> !player.getStrategy().isNice()).count());
    }
}

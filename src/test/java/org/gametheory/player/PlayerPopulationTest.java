package org.gametheory.player;

import org.gametheory.strategy.impl.AlwaysCooperate;
import org.gametheory.strategy.impl.AlwaysDefect;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerPopulationTest {
    @Test
    public void getAlwaysCooperateAndDeflectPlayers() {
        List<Player> players = PlayerPopulation.getAlwaysCooperateAndDeflectPlayers();
        assertTrue(players.get(0).getStrategy().ofType(AlwaysCooperate.class));
        assertTrue(players.get(1).getStrategy().ofType(AlwaysDefect.class));
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
    public void getSameTypeOfPlayers() throws Exception {
        List<Player> players = PlayerPopulation.getDualPlayersWithStrategy(AlwaysCooperate.class);
        assertEquals(2, players.size());
        assertTrue(players.stream().allMatch(player -> player.getStrategy().ofType(AlwaysCooperate.class)));
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

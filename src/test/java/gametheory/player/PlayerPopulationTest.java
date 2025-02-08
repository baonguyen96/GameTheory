package gametheory.player;

import gametheory.player.Player;
import gametheory.player.PlayerPopulation;
import gametheory.strategy.impl.AlwaysCooperate;
import gametheory.strategy.impl.AlwaysDefect;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerPopulationTest {
    private final PlayerPopulation population = new PlayerPopulation(false);

    @Test
    public void getAlwaysCooperateAndDefectPlayers() {
        List<Player> players = population.getAlwaysCooperateAndDefectPlayers();
        assertTrue(players.get(0).getStrategy().ofType(AlwaysCooperate.class));
        assertTrue(players.get(1).getStrategy().ofType(AlwaysDefect.class));
    }

    @Test
    public void getMixedButUniquePlayers() throws Exception {
        List<Player> players = population.getMixedButUniquePlayers();
        assertFalse(players.isEmpty());
    }

    @Test
    public void getOnlyPlayersWhoCooperateFirst() throws Exception {
        List<Player> players = population.getOnlyPlayersWhoCooperateFirst();
        assertTrue(players.stream().allMatch(player -> player.getStrategy().isNice()));
    }

    @Test
    public void getOnlyPlayersWhoDefectFirst() throws Exception {
        List<Player> players = population.getOnlyPlayersWhoDefectFirst();
        assertTrue(players.stream().noneMatch(player -> player.getStrategy().isNice()));
    }

    @Test
    public void getBigPopulationPlayers() throws Exception {
        assertEquals(10, population.getBigPopulationPlayers(10).size());
    }

    @Test
    public void getMixedButUniquePlayersTwice() throws Exception {
        assertEquals(population.getMixedButUniquePlayers().size() * 2, population.getMixedButUniquePlayersTwice().size());
    }

    @Test
    public void getSameTypeOfPlayers() throws Exception {
        List<Player> players = population.getDualPlayersWithStrategy(AlwaysCooperate.class);
        assertEquals(2, players.size());
        assertTrue(players.stream().allMatch(player -> player.getStrategy().ofType(AlwaysCooperate.class)));
    }

    @Test
    public void getCustomPopulation() {
        assertFalse(population.getCustomPopulation().isEmpty());
    }

    @Test
    public void getPopulationWithGoodAndBadPlayers() throws Exception {
        List<Player> players = population.getPopulationWithGoodAndBadPlayers(2, 8);
        assertEquals(2L, players.stream().filter(player -> player.getStrategy().isNice()).count());
        assertEquals(8L, players.stream().filter(player -> !player.getStrategy().isNice()).count());
    }
}

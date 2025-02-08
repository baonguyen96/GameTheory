package gametheory.player;

import gametheory.strategy.impl.AlwaysCooperate;
import gametheory.strategy.impl.AlwaysDefect;
import gametheory.strategy.impl.CooperateFirstButWillDefectTwiceForEachAttack;
import gametheory.strategy.impl.KeepDefectOnceBeingAttacked;
import org.apache.commons.collections.ListUtils;
import gametheory.strategy.Strategy;
import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerPopulation {
    private final boolean confusedPopulation;

    public PlayerPopulation(boolean confusedPopulation) {
        this.confusedPopulation = confusedPopulation;
    }

    public List<Player> getAlwaysCooperateAndDefectPlayers() {
        return Arrays.asList(
                new Player(new AlwaysCooperate(), confusedPopulation),
                new Player(new AlwaysDefect(), confusedPopulation)
        );
    }

    public List<Player> getDualPlayersWithStrategy(Class<? extends Strategy> strategy) throws Exception {
        return getMixedButUniquePlayersTwice()
                .stream()
                .filter(player -> player.getStrategy().ofType(strategy))
                .collect(Collectors.toList());
    }

    public List<Player> getCustomPopulation() {
        return Arrays.asList(
                new Player(new KeepDefectOnceBeingAttacked(), confusedPopulation),
                new Player(new CooperateFirstButWillDefectTwiceForEachAttack(), confusedPopulation),
                new Player(new AlwaysDefect(), confusedPopulation)
        );
    }

    public List<Player> getMixedButUniquePlayers() throws Exception {
        Reflections reflections = new Reflections("gametheory.strategy.impl");
        Set<Class<?>> strategies = new HashSet<>(reflections.getSubTypesOf(Strategy.class));
        List<Player> players = new LinkedList<>();

        for (Class<?> strategy : strategies) {
            players.add(new Player((Strategy) strategy.newInstance(), confusedPopulation));
        }

        return players;
    }

    public List<Player> getMixedButUniquePlayersTwice() throws Exception {
        List<Player> players = getMixedButUniquePlayers();
        List<Player> clones = getMixedButUniquePlayers();
        return ListUtils.union(players, clones);
    }

    public List<Player> getBigPopulationPlayers(int size) throws Exception {
        return getBigPopulationPlayers(size, null);
    }

    private List<Player> getBigPopulationPlayers(int size, Boolean filterStrategyCharacteristic) throws Exception {
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

    public List<Player> getOnlyPlayersWhoCooperateFirst() throws Exception {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, true);
    }

    public List<Player> getOnlyPlayersWhoDefectFirst() throws Exception {
        List<Player> players = getMixedButUniquePlayers();
        return getPlayersWithStrategyCharacteristic(players, false);
    }

    private List<Player> getPlayersWithStrategyCharacteristic(List<Player> players, boolean isNice) {
        return players
                .stream()
                .filter(player -> player.getStrategy().isNice() == isNice)
                .collect(Collectors.toList());
    }

    public List<Player> getPopulationWithGoodAndBadPlayers(int goodPlayerCount, int badPlayerCount) throws Exception {
        List<Player> goodPlayers = getBigPopulationPlayers(goodPlayerCount, true);
        List<Player> badPlayers = getBigPopulationPlayers(badPlayerCount, false);
        return ListUtils.union(goodPlayers, badPlayers);
    }
}

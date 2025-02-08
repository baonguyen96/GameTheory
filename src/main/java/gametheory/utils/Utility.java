package gametheory.utils;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Utility {
    private static final Random random = new Random();

    public static int getRandomNumber(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public static <T> T firstOrDefault(List<T> list, Predicate<T> filter, T defaultValue) {
        if (list == null || list.isEmpty()) {
            return defaultValue;
        }

        return list.stream().filter(filter).findFirst().orElse(defaultValue);
    }
}

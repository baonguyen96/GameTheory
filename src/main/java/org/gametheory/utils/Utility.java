package org.gametheory.utils;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utility {
    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

    public static <T> T firstOrDefault(List<T> list, Predicate<T> filter, T defaultValue) {
        if (list == null || list.isEmpty()) {
            return defaultValue;
        }

        List<T> filteredList = list.stream().filter(filter).collect(Collectors.toList());

        return filteredList.isEmpty() ? defaultValue : filteredList.get(0);
    }
}

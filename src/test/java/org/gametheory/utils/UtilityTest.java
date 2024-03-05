package org.gametheory.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class UtilityTest {
    @Test
    public void getRandomNumber() {
        assertEquals(1, Utility.getRandomNumber(1, 1));
        assertTrue(1 <= Utility.getRandomNumber(1, 10) && Utility.getRandomNumber(1, 10) <= 10);
    }

    @Test
    public void firstOrDefault() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertNull(Utility.firstOrDefault(Collections.emptyList(), null, null));
        assertNull(Utility.firstOrDefault(numbers, n -> n > 10, null));
        assertEquals(2, Utility.firstOrDefault(numbers, n -> n % 2 == 0, null).intValue());
    }
}
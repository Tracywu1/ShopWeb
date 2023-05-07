package com.cc.utils;

import java.util.Random;

public class RandomUsernameGenerator {
    private static final String[] PREFIXES = { "cool", "happy", "brave", "shiny", "super", "funky", "jolly", "bouncy", "fancy", "lucky" };
    private static final String[] SUFFIXES = { "cat", "dog", "fish", "bird", "fox", "bear", "lion", "tiger", "panda", "monkey" };
    private static final Random RANDOM = new Random();

    public static String generate() {
        String prefix = PREFIXES[RANDOM.nextInt(PREFIXES.length)];
        String suffix = SUFFIXES[RANDOM.nextInt(SUFFIXES.length)];
        int number = 1000 + RANDOM.nextInt(9000);
        return prefix + suffix + number;
    }
}

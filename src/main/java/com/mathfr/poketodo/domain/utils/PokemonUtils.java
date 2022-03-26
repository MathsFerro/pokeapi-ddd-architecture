package com.mathfr.poketodo.domain.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonUtils {

    private final static List<Long> randomizedIdList = new ArrayList<>();
    private final static Random random = new Random();
    private final static Integer qtdSaveInDB = 10;

    private PokemonUtils() {}

    public static Long findIdInUrl(String url) {
        int indexId = url.indexOf("pokemon/")+8;
        return Long.valueOf(url.substring(indexId).replace("/", ""));
    }

    public static List<Long> randomizeIds(List<Long> pokemonIdList) {
        randomizedIdList.clear();
        for(int x=0; x<qtdSaveInDB; x++) {
            long number = random.nextInt(pokemonIdList.size()) + 1;
            while (randomizedIdList.contains(number))
                number = random.nextInt(pokemonIdList.size()) + 1;
            randomizedIdList.add(number);
        }

        return randomizedIdList;
    }

}

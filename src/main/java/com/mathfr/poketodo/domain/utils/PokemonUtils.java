package com.mathfr.poketodo.domain.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonUtils {

    private static Integer qtdSaveInDB = 10;

    private PokemonUtils() {}

    public static Long findIdInUrl(String url) {
        int indexId = url.indexOf("pokemon/")+8;
        return Long.valueOf(url.substring(indexId).replace("/", ""));
    }

    public static List<Long> randomizeIds(List<Long> pokemonIdList) {
        List<Long> randomizedIdList = new ArrayList<>();
        Random random = new Random();

        for(int x=0; x<=qtdSaveInDB; x++)
            randomizedIdList.add((long) (random.nextInt(1) + pokemonIdList.size()));

        return randomizedIdList;
    }

}

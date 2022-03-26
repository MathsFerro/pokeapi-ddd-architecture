package com.mathfr.poketodo.domain.utils;

public class PokemonUtils {

    private PokemonUtils() {}

    public static Long findIdInUrl(String url) {
        int indexId = url.indexOf("pokemon/")+8;
        return Long.valueOf(url.substring(indexId).replace("/", ""));
    }

}

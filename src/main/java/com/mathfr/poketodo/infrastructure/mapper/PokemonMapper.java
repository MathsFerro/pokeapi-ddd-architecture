package com.mathfr.poketodo.infrastructure.mapper;

import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.domain.model.Pokemon;

public class PokemonMapper {

    private PokemonMapper() {}

    public static PokemonDTO toDTO(Pokemon pokemon) {
        return PokemonDTO.builder()
                .name(pokemon.getName())
                .build();
    }

    public static Pokemon toEntity(PokemonDTO dto) {
        return Pokemon.builder()
                .name(dto.getName())
                .build();
    }

}

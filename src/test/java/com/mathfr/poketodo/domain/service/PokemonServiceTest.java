package com.mathfr.poketodo.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.domain.model.Pokemon;
import com.mathfr.poketodo.domain.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PokemonServiceTest {

    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private CacheManager cacheManager;
    @MockBean
    private PokemonRepository pokemonRepository;

    @Test
    void findById() {
        when(pokemonRepository.findById(1L))
                .thenReturn(Optional.of(Pokemon
                        .builder()
                        .name("Pikachu")
                        .build()));
        assertNotNull(pokemonService.findById(1L));
    }

    @Test
    void findByIdWithCacheable() {
        when(pokemonRepository.findById(1L))
                .thenReturn(Optional.of(Pokemon
                        .builder()
                        .name("Pikachu")
                        .build()));

        when(pokemonRepository.findById(2L))
                .thenReturn(Optional.of(Pokemon
                        .builder()
                        .name("bulbasaur")
                        .build()));

        PokemonDTO pikachu = pokemonService.findById(1L).get();
        PokemonDTO bulbasaur = pokemonService.findById(2L).get();

        assertNotNull(cacheManager.getCache(pikachu.getName()));
        assertNotNull(cacheManager.getCache(bulbasaur.getName()));
    }

    @Test
    void randomize() {
        final int qtdSave = 10;
        List<PokemonDTO> pokemonList = pokemonService.randomizePokemons();
        assertEquals(qtdSave, pokemonList.size());
    }

}

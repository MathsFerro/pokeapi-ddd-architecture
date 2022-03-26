package com.mathfr.poketodo.domain.service;

import com.mathfr.poketodo.domain.model.Pokemon;
import com.mathfr.poketodo.domain.repository.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PokemonServiceTest {

    @Autowired
    private PokemonService pokemonService;
    @MockBean
    private PokemonRepository pokemonRepository;

    @Test
    void findAll() {
        List<Pokemon> pokemonList = List.of(Pokemon.builder().build());
        assertNotNull(pokemonService.findAll());
    }

    @Test
    void randomize() {
        //pokemonService.sortPokemon();
    }

}

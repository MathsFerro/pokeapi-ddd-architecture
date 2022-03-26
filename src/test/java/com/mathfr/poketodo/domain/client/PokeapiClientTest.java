package com.mathfr.poketodo.domain.client;

import com.mathfr.poketodo.application.dto.PathPokemonDTO;
import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.application.dto.ResourcePokemonDTO;
import com.mathfr.poketodo.domain.utils.PokemonUtils;
import com.mathfr.poketodo.infrastructure.integration.PokeapiClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PokeapiClientTest {

    @Autowired
    private PokeapiClient pokeapiClient;
    @Value("${spring.pokeapi.limit}")
    private Integer limit;

    @Test
    void findPokemons() {
        assertEquals(limit, pokeapiClient.findPokemons().getPathPokemonDTOList().size());
    }

    @Test
    void findPokemonById() {
        ResourcePokemonDTO resourcePokemonDTO = pokeapiClient.findPokemons();
        PathPokemonDTO pathPokemonDTO = resourcePokemonDTO.getPathPokemonDTOList().get(0);
        PokemonDTO pokemonDTO = pokeapiClient.findPokemonById(PokemonUtils.findIdInUrl(pathPokemonDTO.getUrl()));
        assertEquals(pathPokemonDTO.getName(), pokemonDTO.getName());
    }

}

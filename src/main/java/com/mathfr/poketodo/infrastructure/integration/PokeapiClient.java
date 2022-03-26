package com.mathfr.poketodo.infrastructure.integration;

import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.application.dto.ResourcePokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "pokeapi",
        path = "${spring.pokeapi.path}",
        url = "${spring.pokeapi.url}"
)
public interface PokeapiClient {

    @GetMapping
    ResourcePokemonDTO findPokemons();

    @GetMapping(path = "{id}")
    PokemonDTO findPokemonById(@PathVariable("id") Long id);

}

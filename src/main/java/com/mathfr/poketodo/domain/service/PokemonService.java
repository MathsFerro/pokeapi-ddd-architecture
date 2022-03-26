package com.mathfr.poketodo.domain.service;

import com.mathfr.poketodo.application.dto.PathPokemonDTO;
import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.domain.model.Pokemon;
import com.mathfr.poketodo.domain.repository.PokemonRepository;
import com.mathfr.poketodo.domain.utils.PokemonUtils;
import com.mathfr.poketodo.infrastructure.integration.PokeapiClient;
import com.mathfr.poketodo.infrastructure.mapper.PokemonMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PokemonService {

    private PokemonRepository pokemonRepository;
    private PokeapiClient pokeapiClient;

    public List<PokemonDTO> findAll() {
        return pokemonRepository.findAll()
                .stream()
                .map(PokemonMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "pokemon_by_id", key = "#id")
    public Optional<PokemonDTO> findById(Long id) {
        return pokemonRepository.findById(id).map(PokemonMapper::toDTO);
    }

    public List<PokemonDTO> randomizePokemons() {
        try {
            this.pokemonRepository.deleteAll();
            List<Pokemon> pokemonList = this.findRandomizedPokemons(getListPokemonId());
            this.pokemonRepository.saveAll(pokemonList);
            return pokemonList.stream().map(PokemonMapper::toDTO).collect(Collectors.toList());
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<Long> getListPokemonId() {
        return this.findPokemons().stream()
                .map(pathPokemon -> PokemonUtils.findIdInUrl(pathPokemon.getUrl()))
                .collect(Collectors.toList());
    }

    private List<PathPokemonDTO> findPokemons() {
        List<PathPokemonDTO> pathPokemonDTOList = pokeapiClient.findPokemons().getPathPokemonDTOList();
        if(pathPokemonDTOList.isEmpty())
            throw new RuntimeException("Não foi encontrado nenhum PathPokemon");

        return pathPokemonDTOList;
    }

    private List<Pokemon> findRandomizedPokemons(List<Long> pokemonIdList) {
        return PokemonUtils.randomizeIds(pokemonIdList).stream()
                .map(id -> pokeapiClient.findPokemonById(id))
                .map(PokemonMapper::toEntity)
                .collect(Collectors.toList());
    }

}

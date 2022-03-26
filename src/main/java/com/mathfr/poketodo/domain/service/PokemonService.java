package com.mathfr.poketodo.domain.service;

import com.mathfr.poketodo.application.dto.PathPokemonDTO;
import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.application.dto.ResourcePokemonDTO;
import com.mathfr.poketodo.domain.model.Pokemon;
import com.mathfr.poketodo.domain.repository.PokemonRepository;
import com.mathfr.poketodo.domain.utils.PokemonUtils;
import com.mathfr.poketodo.infrastructure.integration.PokeapiClient;
import com.mathfr.poketodo.infrastructure.mapper.PokemonMapper;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PokemonService {

    private PokemonRepository pokemonRepository;
    private PokeapiClient pokeapiClient;

    public void add(PokemonDTO pokemonRequestDTO) {
        pokemonRepository.save(PokemonMapper.toEntity(pokemonRequestDTO));
    }

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

    @Transactional ////////
    public void sortPokemon() {
        try {
            this.pokemonRepository.deleteAll();
            this.pokemonRepository.saveAll(this.findRandomizedPokemons(getListPokemonId()));
        } catch (RuntimeException exception) {
            exception.printStackTrace();
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

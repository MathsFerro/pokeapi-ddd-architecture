package com.mathfr.poketodo.domain.service;

import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.domain.repository.PokemonRepository;
import com.mathfr.poketodo.infrastructure.integration.PokeapiClient;
import com.mathfr.poketodo.infrastructure.mapper.PokemonMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PokemonService {

    private PokemonRepository pokemonRepository;
    private PokeapiClient pokeapiClient;

    public void add(PokemonDTO todoRequestDTO) {
        pokemonRepository.save(PokemonMapper.toEntity(todoRequestDTO));
    }

    public List<PokemonDTO> findAll() {
        return pokemonRepository.findAll()
                .stream()
                .map(PokemonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PokemonDTO> findById(Long id) {
        return pokemonRepository.findById(id).map(PokemonMapper::toDTO);
    }
}

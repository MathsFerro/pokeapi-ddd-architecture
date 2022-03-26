package com.mathfr.poketodo.application.rest;

import com.mathfr.poketodo.application.dto.PokemonDTO;
import com.mathfr.poketodo.domain.service.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class PokemonController {

    private PokemonService todoService;

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> findAll() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("{id}")
    public ResponseEntity<PokemonDTO> findById(@PathVariable("id") Long id) {
        return todoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}

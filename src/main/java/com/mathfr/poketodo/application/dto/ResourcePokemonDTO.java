package com.mathfr.poketodo.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourcePokemonDTO {

    @JsonProperty(value = "results")
    private List<PathPokemonDTO> pathPokemonDTOList;

}




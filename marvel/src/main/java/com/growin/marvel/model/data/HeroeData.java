package com.growin.marvel.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growin.marvel.model.entities.Character;
import lombok.Data;

import java.util.Collection;

@Data
public class HeroeData {
    @JsonProperty("results")
    private Collection<Character> results;
}

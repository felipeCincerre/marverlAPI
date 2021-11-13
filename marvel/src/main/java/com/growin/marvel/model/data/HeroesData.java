package com.growin.marvel.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growin.marvel.model.entities.Character;
import lombok.Data;

import java.util.Collection;

@Data
public class HeroesData {

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("limit")
    private Integer limit;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("results")
    private Collection<Character> results;
}

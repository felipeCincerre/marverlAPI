package com.growin.marvel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growin.marvel.model.data.HeroeData;
import lombok.Data;

@Data
public class Heroe {

    @JsonProperty("data")
    private HeroeData data;
}

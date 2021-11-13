package com.growin.marvel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.growin.marvel.model.data.HeroesData;
import lombok.Data;

@Data
public class Heroes {

    @JsonProperty("data")
    private HeroesData data;
}

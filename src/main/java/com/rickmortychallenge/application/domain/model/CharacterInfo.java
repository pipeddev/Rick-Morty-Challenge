package com.rickmortychallenge.application.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CharacterInfo {
    @JsonProperty
    public int id;
    @JsonProperty
    public String name;
    @JsonProperty
    public String status;
    @JsonProperty
    public String species;
    @JsonProperty
    public String type;
    @JsonProperty
    public int episode_count;
    @JsonProperty
    public OriginInfo origin;
}

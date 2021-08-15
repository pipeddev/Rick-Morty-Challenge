package com.rickmortychallenge.application.data.character.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CharacterEntity {
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
    public String gender;
    @JsonProperty
    public OriginEntity origin;
    @JsonProperty
    public LocationCharacterEntity location;
    @JsonProperty
    public String image;
    @JsonProperty
    public List<String> episode;
    @JsonProperty
    public String url;
    @JsonProperty
    public String created;
}

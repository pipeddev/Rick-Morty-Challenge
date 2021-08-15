package com.rickmortychallenge.application.data.location.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LocationEntity {
    @JsonProperty
    public int id;
    @JsonProperty
    public String name;
    @JsonProperty
    public String type;
    @JsonProperty
    public String dimension;
    @JsonProperty
    public List<String> residents;
    @JsonProperty
    public String url;
    @JsonProperty
    public String created;
}

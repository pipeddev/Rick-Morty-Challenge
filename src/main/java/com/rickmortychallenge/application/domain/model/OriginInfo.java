package com.rickmortychallenge.application.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OriginInfo {
    @JsonProperty
    public String name;
    @JsonProperty
    public String url;
    @JsonProperty
    public String dimension;
    @JsonProperty
    public List<String> residents;
}

package com.rickmortychallenge.configuration.location;

import org.springframework.beans.factory.annotation.Value;

public class LocationProperties {
    @Value("${api.baseURL}")
    private String baseURL;

    @Value("${api.paths.locations}")
    private String locationsEndpoint;

    @Value("${api.paths.location}")
    private String locationEndpoint;

    public String getLocationURL(String characterId) {
        return baseURL.concat(locationEndpoint).replace("{characterId}", characterId);
    }

    public String getLocationsURL() {
        return baseURL.concat(locationsEndpoint);
    }
}

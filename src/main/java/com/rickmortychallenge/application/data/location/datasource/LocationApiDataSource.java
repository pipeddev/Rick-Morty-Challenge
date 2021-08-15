package com.rickmortychallenge.application.data.location.datasource;

import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.configuration.location.LocationProperties;
import org.springframework.web.client.RestOperations;

public class LocationApiDataSource implements LocationDataSource{
    public final LocationProperties locationProperties;
    public final RestOperations restOperations;

    public LocationApiDataSource(LocationProperties locationProperties, RestOperations restOperations) {
        this.locationProperties = locationProperties;
        this.restOperations = restOperations;
    }

    @Override
    public LocationEntity getLocation(String code) {
        return restOperations.getForObject(locationProperties.getLocationURL(code), LocationEntity.class);
    }
}

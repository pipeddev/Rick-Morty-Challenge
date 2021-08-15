package com.rickmortychallenge.configuration.location;

import com.rickmortychallenge.application.data.location.datasource.LocationApiDataSource;
import com.rickmortychallenge.application.data.location.datasource.LocationDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class LocationConfiguration {

    @Bean
    LocationDataSource locationDataSource(RestOperations restOperations, LocationProperties locationProperties){
        return new LocationApiDataSource(locationProperties, restOperations);
    }

    @Bean
    RestOperations restOperations() {return new RestTemplate();}

    @Bean
    LocationProperties locationProperties() { return new LocationProperties();}
}

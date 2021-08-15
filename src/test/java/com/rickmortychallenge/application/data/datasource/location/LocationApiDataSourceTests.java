package com.rickmortychallenge.application.data.datasource.location;

import com.rickmortychallenge.application.data.location.datasource.LocationApiDataSource;
import com.rickmortychallenge.application.data.location.datasource.LocationDataSource;
import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.configuration.location.LocationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LocationApiDataSourceTests {
    private LocationApiDataSource locationApiDataSource;

    private LocationEntity locationEntity;

    @Mock
    private LocationProperties locationProperties;

    @Mock
    private RestOperations restOperations;

    private final String DATA_CHARACTER_CODE = "1";

    private final int DATA_LOCATION_ID = 1;
    private final String DATA_LOCATION_NAME = "Citadel of Ricks";
    private final String DATA_LOCATION_TYPE = "Space station";
    private final String DATA_LOCATION_DIMENSION = "unknown";
    private final String DATA_LOCATION_RESIDENT = "https://rickandmortyapi.com/api/character/8";
    private final String DATA_LOCATION_URL = "https://rickandmortyapi.com/api/location/3";
    private final String DATA_LOCATION_CREATED = "2017-11-10T13:08:13.191Z";

    private List<String> residents;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        locationApiDataSource = new LocationApiDataSource(locationProperties, restOperations);

        residents = new ArrayList<>();
        residents.add(DATA_LOCATION_RESIDENT);

        locationEntity = new LocationEntity();
        locationEntity.id = DATA_LOCATION_ID;
        locationEntity.name = DATA_LOCATION_NAME;
        locationEntity.type = DATA_LOCATION_TYPE;
        locationEntity.dimension = DATA_LOCATION_DIMENSION;
        locationEntity.url = DATA_LOCATION_URL;
        locationEntity.created = DATA_LOCATION_CREATED;
        locationEntity.residents = residents;
    }

    @Test
    public void shouldReturnValidInstanceOfLocationDatasource_whenCreated() {
        assertThat(locationApiDataSource, instanceOf(LocationDataSource.class));
    }

    @Test
    public void shouldReturnLocationEntity_whenRequestIsExecuted() {
        when(restOperations.getForObject(locationProperties.getLocationURL(DATA_CHARACTER_CODE), LocationEntity.class)).thenReturn(locationEntity);
        LocationEntity locationEntity = locationApiDataSource.getLocation(DATA_CHARACTER_CODE);
        assertEquals(DATA_LOCATION_ID, locationEntity.id);
        assertEquals(DATA_LOCATION_NAME, locationEntity.name);
        assertEquals(DATA_LOCATION_TYPE, locationEntity.type);
        assertEquals(DATA_LOCATION_DIMENSION, locationEntity.dimension);
        assertEquals(residents, locationEntity.residents);
        assertEquals(DATA_LOCATION_URL, locationEntity.url);
        assertEquals(DATA_LOCATION_CREATED, locationEntity.created);
    }
}

package com.rickmortychallenge.application.data.mapper;

import com.rickmortychallenge.application.data.character.mapper.OriginInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.application.domain.model.OriginInfo;
import com.rickmortychallenge.common.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OriginInfoToCharacterEntityMapperTests {

    private Mapper<OriginInfo, LocationEntity> originInfoToCharacterEntityMapper;

    private LocationEntity locationEntity;
    private OriginInfo originInfo;

    private final int DATA_LOCATION_ID = 1;
    private final String DATA_LOCATION_NAME = "Citadel of Ricks";
    private final String DATA_LOCATION_TYPE = "Space station";
    private final String DATA_LOCATION_DIMENSION = "unknown";
    private final String DATA_LOCATION_RESIDENT = "https://rickandmortyapi.com/api/character/8";
    private final String DATA_LOCATION_URL = "https://rickandmortyapi.com/api/location/3";
    private final String DATA_LOCATION_CREATED = "2017-11-10T13:08:13.191Z";

    private List<String> residents;

    @BeforeEach
    public void setUp(){
        residents = new ArrayList<>();
        residents.add(DATA_LOCATION_RESIDENT);
        originInfoToCharacterEntityMapper = new OriginInfoToCharacterEntityMapper();
        originInfo = new OriginInfo();
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
    public void shouldReturnValidOriginInfo_whenReverseMapIsCalled() {
        OriginInfo originInfo = originInfoToCharacterEntityMapper.reverseMap(locationEntity);
        assertEquals(DATA_LOCATION_NAME, originInfo.name);
        assertEquals(DATA_LOCATION_DIMENSION, originInfo.dimension);
        assertEquals(residents, originInfo.residents);
        assertEquals(DATA_LOCATION_URL, originInfo.url);
        // assertEquals(DATA_CHARACTER_INFO_NAME, characterInfo.name);
    }

    @Test
    public void shouldReturnNull_whenMapIsCalled() {
        assertEquals(null, originInfoToCharacterEntityMapper.map(originInfo));
    }
}

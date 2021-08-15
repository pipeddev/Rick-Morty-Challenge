package com.rickmortychallenge.application.data.repository;

import com.rickmortychallenge.application.data.character.datasource.CharacterApiDataSource;
import com.rickmortychallenge.application.data.character.datasource.CharacterDataSource;
import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.data.character.entity.LocationCharacterEntity;
import com.rickmortychallenge.application.data.character.entity.OriginEntity;
import com.rickmortychallenge.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.data.character.mapper.OriginInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.data.character.repository.CharacterApiRepository;
import com.rickmortychallenge.application.data.location.datasource.LocationDataSource;
import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.model.OriginInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;
import com.rickmortychallenge.common.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CharacterApiRepositoryTests {
    private CharacterApiRepository characterApiRepository;

    @Mock
    private CharacterDataSource characterDatasource;

    @Mock
    private LocationDataSource locationDataSource;
    private Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;
    private Mapper<OriginInfo, LocationEntity> originInfoToCharacterEntityMapper;

    private final String DATA_CHARACTER_CODE = "1";

    private final int DATA_CHARACTER_ID = 1;
    private final String DATA_CHARACTER_STATUS = "Alive";
    private final String DATA_CHARACTER_SPECIES = "Human";
    private final String DATA_CHARACTER_TYPE = "Unknown";
    private final String DATA_CHARACTER_GENDER = "Male";
    private final String DATA_CHARACTER_IMAGE = "https://rickandmortyapi.com/api/character/avatar/1.jpeg";
    private final String DATA_CHARACTER_URL = "https://rickandmortyapi.com/api/character/1";
    private final String DATA_CHARACTER_CREATED = "Jun 01 2000";
    private final String DATA_CHARACTER_NAME = "Ricky";

    private final String DATA_ORIGIN_NAME = "Earth";
    private final String DATA_ORIGIN_URL = "https://rickandmortyapi.com/api/location/1";

    private final String DATA_LOCATION_NAME = "Galaxy 1";
    private final String DATA_LOCATION_URL = "https://rickandmortyapi.com/api/location/10";

    private final String DATA_EPISODE = "https://rickandmortyapi.com/api/episode/1";

    private final int DATA_LOCATION_ID = 1;
    private final String DATA_LOCATION_ENTITY_NAME = "Citadel of Ricks";
    private final String DATA_LOCATION_ENTITY_TYPE = "Space station";
    private final String DATA_LOCATION_ENTITY_DIMENSION = "unknown";
    private final String DATA_LOCATION_ENTITY_RESIDENT = "https://rickandmortyapi.com/api/character/8";
    private final String DATA_LOCATION_ENTITY_URL = "https://rickandmortyapi.com/api/location/3";
    private final String DATA_LOCATION_ENTITY_CREATED = "2017-11-10T13:08:13.191Z";


    private final String DATA_CHARACTER_INFO_NAME = "Ricky";
    private final String DATA_CHARACTER_INFO_STATUS = "Alive";
    private final String DATA_CHARACTER_INFO_SPECIES = "Human";


    private CharacterApiDataSource characterApiDatasource;
    private CharacterEntity characterEntity;
    private List<String> episode;
    private LocationCharacterEntity locationCharacterEntity;
    private OriginEntity origin;
    private LocationEntity locationEntity;
    private List<String> residents;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        residents = new ArrayList<>();
        residents.add(DATA_LOCATION_ENTITY_RESIDENT);

        characterInfoToCharacterEntityMapper = new CharacterInfoToCharacterEntityMapper();
        originInfoToCharacterEntityMapper = new OriginInfoToCharacterEntityMapper();

        characterApiRepository = new CharacterApiRepository(
                characterDatasource,
                locationDataSource,
                characterInfoToCharacterEntityMapper,
                originInfoToCharacterEntityMapper
        );


        episode = new ArrayList<>();
        episode.add(DATA_EPISODE);

        origin = new OriginEntity();
        origin.name = DATA_ORIGIN_NAME;
        origin.url = DATA_ORIGIN_URL;

        locationCharacterEntity = new LocationCharacterEntity();
        locationCharacterEntity.name = DATA_LOCATION_NAME;
        locationCharacterEntity.url = DATA_LOCATION_URL;

        characterEntity = new CharacterEntity();
        characterEntity.id = DATA_CHARACTER_ID;
        characterEntity.episode = episode;
        characterEntity.created = DATA_CHARACTER_CREATED;
        characterEntity.gender = DATA_CHARACTER_GENDER;
        characterEntity.image = DATA_CHARACTER_IMAGE;
        characterEntity.location = locationCharacterEntity;
        characterEntity.name = DATA_CHARACTER_NAME;
        characterEntity.url = DATA_CHARACTER_URL;
        characterEntity.species = DATA_CHARACTER_SPECIES;
        characterEntity.status = DATA_CHARACTER_STATUS;
        characterEntity.type = DATA_CHARACTER_TYPE;
        characterEntity.origin = origin;

        locationEntity = new LocationEntity();
        locationEntity.id = DATA_LOCATION_ID;
        locationEntity.name = DATA_LOCATION_ENTITY_NAME;
        locationEntity.type = DATA_LOCATION_ENTITY_TYPE;
        locationEntity.dimension = DATA_LOCATION_ENTITY_DIMENSION;
        locationEntity.residents = residents;
        locationEntity.url = DATA_LOCATION_ENTITY_URL;
        locationEntity.created = DATA_LOCATION_ENTITY_CREATED;

    }

    @Test
    public void shouldBeValidInstanceOfCharacterRepository_whenCreated() {
        assertThat(characterApiRepository, instanceOf(CharacterRepository.class));
    }

    @Test
    public void shouldReturnValidCharacterInfo_whenGetInfoIsCalled() {
        when(characterDatasource.getCharacter(DATA_CHARACTER_CODE)).thenReturn(characterEntity);
        when(locationDataSource.getLocation(DATA_CHARACTER_CODE)).thenReturn(locationEntity);

        CharacterInfo characterInfo = characterApiRepository.getInfo(DATA_CHARACTER_CODE);

        assertEquals(DATA_CHARACTER_INFO_NAME, characterInfo.name);
        assertEquals(DATA_CHARACTER_INFO_SPECIES, characterInfo.species);
        assertEquals(DATA_CHARACTER_INFO_STATUS, characterInfo.status);
        assertEquals(DATA_LOCATION_ENTITY_NAME, characterInfo.origin.name);
        assertEquals(DATA_LOCATION_ENTITY_DIMENSION, characterInfo.origin.dimension);
    }
}

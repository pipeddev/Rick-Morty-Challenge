package com.rickmortychallenge.application.data.datasource.character;

import com.rickmortychallenge.application.data.character.datasource.CharacterApiDataSource;
import com.rickmortychallenge.application.data.character.datasource.CharacterDataSource;
import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.data.character.entity.OriginEntity;
import com.rickmortychallenge.application.data.character.entity.LocationEntity;
import com.rickmortychallenge.configuration.character.CharacterProperties;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class CharacterApiDataSourceTests {

    private final String DATA_CHARACTER_CODE = "1";

    private final int DATA_CHARACTER_ID = 1;
    private final String DATA_CHARACTER_STATUS = "Alive";
    private final String DATA_CHARACTER_SPECIES = "Human";
    private final String DATA_CHARACTER_TYPE = "Unknown";
    private final String DATA_CHARACTER_GENDER = "Male";
    private final String DATA_CHARACTER_IMAGE = "https://example.com/image";
    private final String DATA_CHARACTER_URL = "https://example.com/url";
    private final String DATA_CHARACTER_CREATED = "Jun 01 2000";
    private final String DATA_CHARACTER_NAME = "Ricky";

    private final String DATA_ORIGIN_NAME = "Earth";
    private final String DATA_ORIGIN_URL = "https://example.com/earth";

    private final String DATA_LOCATION_NAME = "Galaxy 1";
    private final String DATA_LOCATION_URL = "https://example.com/galaxy1";

    private final String DATA_EPISODE = "1";


    private CharacterApiDataSource characterApiDatasource;
    private CharacterEntity characterEntity;
    private List<String> episode;
    private LocationEntity location;
    private OriginEntity origin;

    @Mock
    private RestOperations restOperations;

    @Mock
    private CharacterProperties characterProperties;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        characterApiDatasource = new CharacterApiDataSource(characterProperties, restOperations);

        episode = new ArrayList<>();
        episode.add(DATA_EPISODE);

        origin = new OriginEntity();
        origin.name = DATA_ORIGIN_NAME;
        origin.url = DATA_ORIGIN_URL;

        location = new LocationEntity();
        location.name = DATA_LOCATION_NAME;
        location.url = DATA_LOCATION_URL;

        characterEntity = new CharacterEntity();
        characterEntity.id = DATA_CHARACTER_ID;
        characterEntity.episode = episode;
        characterEntity.created = DATA_CHARACTER_CREATED;
        characterEntity.gender = DATA_CHARACTER_GENDER;
        characterEntity.image = DATA_CHARACTER_IMAGE;
        characterEntity.location = location;
        characterEntity.name = DATA_CHARACTER_NAME;
        characterEntity.url = DATA_CHARACTER_URL;
        characterEntity.species = DATA_CHARACTER_SPECIES;
        characterEntity.status = DATA_CHARACTER_STATUS;
        characterEntity.type = DATA_CHARACTER_TYPE;
        characterEntity.origin = origin;
    }

    @Test
    public void shouldReturnValidInstanceOfCharacterDatasource_whenCreated() {
        assertThat(characterApiDatasource, instanceOf(CharacterDataSource.class));
    }

    @Test
    public void shouldReturnCharacterEntity_whenRequestIsExecuted() {
        when(restOperations.getForObject(characterProperties.getCharacterURL(DATA_CHARACTER_CODE), CharacterEntity.class)).thenReturn(characterEntity);
        CharacterEntity characterEntity = characterApiDatasource.getCharacter(DATA_CHARACTER_CODE);
        assertEquals(DATA_CHARACTER_ID, characterEntity.id);
        assertEquals(DATA_CHARACTER_CREATED, characterEntity.created);
        assertEquals(DATA_CHARACTER_GENDER, characterEntity.gender);
        assertEquals(DATA_CHARACTER_IMAGE, characterEntity.image);
        assertEquals(DATA_CHARACTER_NAME, characterEntity.name);
        assertEquals(DATA_CHARACTER_SPECIES, characterEntity.species);
        assertEquals(DATA_CHARACTER_STATUS, characterEntity.status);
        assertEquals(DATA_CHARACTER_TYPE, characterEntity.type);
        assertEquals(DATA_CHARACTER_URL, characterEntity.url);
        assertEquals(DATA_LOCATION_NAME, characterEntity.location.name);
        assertEquals(DATA_LOCATION_URL, characterEntity.location.url);
        assertEquals(DATA_ORIGIN_NAME, characterEntity.origin.name);
        assertEquals(DATA_ORIGIN_URL, characterEntity.origin.url);
    }
}

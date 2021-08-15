package com.rickmortychallenge.application.data.mapper;

import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.data.character.entity.LocationCharacterEntity;
import com.rickmortychallenge.application.data.character.entity.OriginEntity;
import com.rickmortychallenge.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.common.mapper.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CharacterInfoToCharacterEntityMapperTests {

    private Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;
    private CharacterInfo characterInfo;
    private CharacterEntity characterEntity;
    private LocationCharacterEntity location;
    private OriginEntity origin;

    private final String DATA_CHARACTER_INFO_NAME = "Ricky";
    private final String DATA_CHARACTER_INFO_STATUS = "Alive";
    private final String DATA_CHARACTER_INFO_SPECIES = "Human";

    private final int DATA_CHARACTER_ID = 1;
    private final String DATA_CHARACTER_STATUS = "Alive";
    private final String DATA_CHARACTER_SPECIES = "Human";
    private final String DATA_CHARACTER_TYPE = "Unknown";
    private final String DATA_CHARACTER_GENDER = "Male";
    private final String DATA_CHARACTER_IMAGE = "https://rickandmortyapi.com/api/character/avatar/1.jpeg";
    private final String DATA_CHARACTER_URL = "https://rickandmortyapi.com/api/character/1";
    private final String DATA_CHARACTER_CREATED = "Jun 01 2000";
    private final String DATA_CHARACTER_NAME = "Ricky";
    private final String DATA_CHARACTER_EPISODE = "1";

    private final String DATA_ORIGIN_NAME = "Earth";
    private final String DATA_ORIGIN_URL = "https://rickandmortyapi.com/api/location/1";

    private final String DATA_LOCATION_NAME = "Galaxy 1";
    private final String DATA_LOCATION_URL = "https://rickandmortyapi.com/api/location/10";

    private List<String> episode;

    @BeforeEach
    public void setUp(){
        episode = new ArrayList<>();
        episode.add(DATA_CHARACTER_EPISODE);
        characterInfoToCharacterEntityMapper = new CharacterInfoToCharacterEntityMapper();
        characterInfo = new CharacterInfo();
        characterInfo.name = DATA_CHARACTER_INFO_NAME;
        characterInfo.species = DATA_CHARACTER_INFO_SPECIES;
        characterInfo.status = DATA_CHARACTER_INFO_STATUS;
        characterInfo.episode_count = 1;

        origin = new OriginEntity();
        origin.name = DATA_ORIGIN_NAME;
        origin.url = DATA_ORIGIN_URL;

        location = new LocationCharacterEntity();
        location.name = DATA_LOCATION_NAME;
        location.url = DATA_LOCATION_URL;

        characterEntity = new CharacterEntity();
        characterEntity.id = DATA_CHARACTER_ID;
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
        characterEntity.episode = episode;
    }

    @Test
    public void shouldReturnValidCharacterInfo_whenReverseMapIsCalled() {
        CharacterInfo characterInfo = characterInfoToCharacterEntityMapper.reverseMap(characterEntity);
        assertEquals(DATA_CHARACTER_INFO_NAME, characterInfo.name);
        assertEquals(DATA_CHARACTER_INFO_SPECIES, characterInfo.species);
        assertEquals(DATA_CHARACTER_INFO_STATUS, characterInfo.status);
        assertEquals(null, characterInfo.origin);
    }

    @Test
    public void shouldReturnNull_whenMapIsCalled() {
        assertEquals(null, characterInfoToCharacterEntityMapper.map(characterInfo));
    }
}

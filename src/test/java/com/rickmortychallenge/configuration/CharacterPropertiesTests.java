package com.rickmortychallenge.configuration;

import com.rickmortychallenge.configuration.character.CharacterProperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterPropertiesTests {
    private CharacterProperties characterProperties;

    private final static String DATA_CHARACTER_ID = "123";
    private final static String DATA_BASEURL = "https://rickandmortyapi.com/api/";
    private final static String DATA_CHARACTER_ENDPOINT = "character/{characterId}";
    private final static String DATA_CHARACTERS_ENDPOINT = "character";
    private final static String DATA_CHARACTER_FULL_URL = "https://rickandmortyapi.com/api/character/123";

    @BeforeEach
    public void setUp() {
        characterProperties = new CharacterProperties();
        ReflectionTestUtils.setField(characterProperties, "baseURL", DATA_BASEURL);
        ReflectionTestUtils.setField(characterProperties, "characterEndpoint", DATA_CHARACTER_ENDPOINT);
        ReflectionTestUtils.setField(characterProperties, "charactersEndpoint", DATA_CHARACTERS_ENDPOINT);
    }

    @Test
    public void shouldReturnAValidEpisodesURL() {
        assertNotNull(characterProperties.getCharactersURL());
    }

    @Test
    public void shouldReturnAValidEpisodeURL() {
        assertNotNull(characterProperties.getCharacterURL(DATA_CHARACTER_ID));
        assertEquals(characterProperties.getCharacterURL(DATA_CHARACTER_ID), DATA_CHARACTER_FULL_URL);
        //Assert.assertNotNull(characterProperties.getCharacterURL(DATA_CHARACTER_ID));
        //Assert.assertEquals(characterProperties.getCharacterURL(DATA_CHARACTER_ID), DATA_CHARACTER_FULL_URL);
    }
}

package com.rickmortychallenge.application.presentation.controller;

import com.rickmortychallenge.application.Character;
import com.rickmortychallenge.application.CharacterController;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.model.OriginInfo;
import com.rickmortychallenge.application.domain.usecase.CharacterInfoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CharacterControllerTests {
    private CharacterController characterController;
    private CharacterInfo characterInfo;

    private final String DATA_CHARACTER_CODE = "1";
    private final String DATA_CHARACTER_NAME = "character name";
    private final String DATA_CHARACTER_STATUS = "status";
    private final String DATA_CHARACTER_SPECIES = "species";
    private final String DATA_ORIGIN_NAME = "Earth (C-137)";
    private final String DATA_ORIGIN_URL = "https://rickandmortyapi.com/api/location/1";
    private final String DATA_ORIGIN_DIMENSION = "Dimension C-137";
    private final String DATA_LOCATION_ENTITY_RESIDENT = "https://rickandmortyapi.com/api/character/8";


    private List<String> residents;

    @Mock
    private CharacterInfoUseCase characterInfoUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        residents = new ArrayList<>();
        residents.add(DATA_LOCATION_ENTITY_RESIDENT);
        characterController = new CharacterController(characterInfoUseCase);

        characterInfo = new CharacterInfo();
        characterInfo.name = DATA_CHARACTER_NAME;
        characterInfo.status = DATA_CHARACTER_STATUS;
        characterInfo.species = DATA_CHARACTER_SPECIES;

        OriginInfo originInfo = new OriginInfo();
        originInfo.name = DATA_ORIGIN_NAME;
        originInfo.url = DATA_ORIGIN_URL;
        originInfo.dimension = DATA_ORIGIN_DIMENSION;
        originInfo.residents = residents;
        characterInfo.origin = originInfo;
    }

    @Test
    public void shouldReturnValidInstanceOfCharacter_WhenCreated() {
        assertThat(characterController, instanceOf(Character.class));
    }

    @Test
    public void shouldReturnValidCharacterInfoModel_WhenCalledWithValidParameters() {
        when(characterInfoUseCase.executeWith(DATA_CHARACTER_CODE)).thenReturn(characterInfo);

        CharacterInfo characterInfo = characterController.informationCharacter(DATA_CHARACTER_CODE).getBody();

        assertEquals(DATA_CHARACTER_NAME, characterInfo.name);

        assertEquals(DATA_CHARACTER_SPECIES, characterInfo.species);
        assertEquals(DATA_CHARACTER_STATUS, characterInfo.status);
        assertEquals(DATA_ORIGIN_NAME ,characterInfo.origin.name);
        assertEquals(DATA_ORIGIN_URL ,characterInfo.origin.url);
        assertEquals(DATA_ORIGIN_DIMENSION ,characterInfo.origin.dimension);
    }
}

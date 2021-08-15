package com.rickmortychallenge.application.domain.usecase;

import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CharacterInfoUseCaseTests {
    private CharacterInfoUseCase characterInfoUseCase;

    private final String DATA_CHARACTER_CODE = "1";

    @Mock
    private CharacterInfo characterInfo;

    @Mock
    private CharacterRepository characterRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        characterInfoUseCase = new CharacterInfoUseCase(characterRepository);
    }

    @Test
    public void shouldReturnValidCharacterInfo_whenExecuteIsCalled() {
        when(characterRepository.getInfo(DATA_CHARACTER_CODE)).thenReturn(characterInfo);

        CharacterInfo characterInfo = characterInfoUseCase.executeWith(DATA_CHARACTER_CODE);

        assertNotNull(characterInfo);
    }

    @Test
    public void shouldReturnNullValue_whenCodeIsIncorrect() {
        when(characterRepository.getInfo("code")).thenReturn(null);

        CharacterInfo characterInfo = characterInfoUseCase.executeWith("code");

        assertNull(characterInfo);
    }
}

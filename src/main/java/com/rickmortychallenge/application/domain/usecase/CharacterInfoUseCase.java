package com.rickmortychallenge.application.domain.usecase;

import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;

public class CharacterInfoUseCase {
    private final CharacterRepository characterRepository;

    public CharacterInfoUseCase(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public CharacterInfo executeWith(String code) {
        return characterRepository.getInfo(code);
    }
}

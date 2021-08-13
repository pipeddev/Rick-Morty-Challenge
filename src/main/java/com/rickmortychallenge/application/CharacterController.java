package com.rickmortychallenge.application;

import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.usecase.CharacterInfoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CharacterController implements Character{
    private final CharacterInfoUseCase characterInfoUseCase;

    public CharacterController(CharacterInfoUseCase characterInfoUseCase) {
        this.characterInfoUseCase = characterInfoUseCase;
    }

    @Override
    public ResponseEntity<CharacterInfo> informationCharacter(String id) {
        CharacterInfo characterInfo = characterInfoUseCase.executeWith(id);
        return new ResponseEntity<>(characterInfo, HttpStatus.OK);
    }
}

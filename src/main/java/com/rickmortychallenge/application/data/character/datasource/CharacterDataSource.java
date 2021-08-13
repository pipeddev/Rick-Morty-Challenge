package com.rickmortychallenge.application.data.character.datasource;

import com.rickmortychallenge.application.data.character.entity.CharacterEntity;

public interface CharacterDataSource {
    CharacterEntity getCharacter(String code);
}

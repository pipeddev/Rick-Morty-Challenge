package com.rickmortychallenge.application.data.character.mapper;

import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.common.mapper.Mapper;

public class CharacterInfoToCharacterEntityMapper extends Mapper<CharacterInfo, CharacterEntity> {
    @Override public CharacterEntity map(CharacterInfo value) {
        return null;
    }

    @Override public CharacterInfo reverseMap(CharacterEntity value) {
        CharacterInfo characterInfo = new CharacterInfo();
        characterInfo.id = value.id;
        characterInfo.name = value.name;
        //characterInfo.gender = value.gender;
        characterInfo.status = value.status;
        characterInfo.species = value.species;
        characterInfo.type = value.type;
        characterInfo.episode_count = value.episode.size();
        return characterInfo;
    }
}

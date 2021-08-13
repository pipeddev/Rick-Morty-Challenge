package com.rickmortychallenge.application.data.character.repository;

import com.rickmortychallenge.application.data.character.datasource.CharacterDataSource;
import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;
import com.rickmortychallenge.common.mapper.Mapper;

public class CharacterApiRepository implements CharacterRepository {

    private final CharacterDataSource characterDatasource;
    //private final EpisodeDataSource episodeDatasource;
    private final Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;

    public CharacterApiRepository(CharacterDataSource characterDatasource, Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper) {
        this.characterDatasource = characterDatasource;
        this.characterInfoToCharacterEntityMapper = characterInfoToCharacterEntityMapper;
    }

    @Override
    public CharacterInfo getInfo(String code) {
        CharacterEntity characterEntity = characterDatasource.getCharacter(code);
        CharacterInfo characterInfo = characterInfoToCharacterEntityMapper.reverseMap(characterEntity);
        return characterInfo;
    }
}

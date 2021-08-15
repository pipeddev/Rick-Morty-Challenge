package com.rickmortychallenge.application.data.character.repository;

import com.rickmortychallenge.application.data.character.datasource.CharacterDataSource;
import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.data.location.datasource.LocationDataSource;
import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.model.OriginInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;
import com.rickmortychallenge.common.mapper.Mapper;

public class CharacterApiRepository implements CharacterRepository {

    private final CharacterDataSource characterDatasource;
    private final LocationDataSource locationDataSource;
    private final Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper;
    private final Mapper<OriginInfo, LocationEntity> originInfoToCharacterEntityMapper;

    public CharacterApiRepository(CharacterDataSource characterDatasource, LocationDataSource locationDataSource, Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper, Mapper<OriginInfo, LocationEntity> originInfoToCharacterEntityMapper) {
        this.characterDatasource = characterDatasource;
        this.locationDataSource = locationDataSource;
        this.characterInfoToCharacterEntityMapper = characterInfoToCharacterEntityMapper;
        this.originInfoToCharacterEntityMapper = originInfoToCharacterEntityMapper;
    }

    @Override
    public CharacterInfo getInfo(String code) {
        CharacterEntity characterEntity = characterDatasource.getCharacter(code);
        CharacterInfo characterInfo = characterInfoToCharacterEntityMapper.reverseMap(characterEntity);
        characterInfo.origin = getOrigin(code);
        return characterInfo;
    }

    private OriginInfo getOrigin(String code) {
        LocationEntity locationEntity = locationDataSource.getLocation(code);
        return originInfoToCharacterEntityMapper.reverseMap(locationEntity);
    }
}

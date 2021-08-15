package com.rickmortychallenge.application.data.character.mapper;

import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.application.domain.model.OriginInfo;
import com.rickmortychallenge.common.mapper.Mapper;

public class OriginInfoToCharacterEntityMapper extends Mapper<OriginInfo, LocationEntity> {

    @Override
    public LocationEntity map(OriginInfo value) {
        return null;
    }

    @Override
    public OriginInfo reverseMap(LocationEntity value) {
        OriginInfo originInfo = new OriginInfo();
        originInfo.name = value.name;
        originInfo.url = value.url;
        originInfo.dimension = value.dimension;
        originInfo.residents = value.residents;
        return originInfo;
    }
}

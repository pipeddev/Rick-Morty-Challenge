package com.rickmortychallenge.application.data.location.datasource;

import com.rickmortychallenge.application.data.location.entity.LocationEntity;

public interface LocationDataSource {
    LocationEntity getLocation(String code);
}

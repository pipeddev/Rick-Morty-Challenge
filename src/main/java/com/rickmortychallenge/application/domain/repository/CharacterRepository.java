package com.rickmortychallenge.application.domain.repository;

import com.rickmortychallenge.application.domain.model.CharacterInfo;

public interface CharacterRepository {
    CharacterInfo getInfo(String code);
}

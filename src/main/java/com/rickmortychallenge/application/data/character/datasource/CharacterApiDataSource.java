package com.rickmortychallenge.application.data.character.datasource;

import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.configuration.character.CharacterProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.server.ResponseStatusException;

public class CharacterApiDataSource implements CharacterDataSource{
    private final CharacterProperties characterProperties;
    private final RestOperations restOperations;

    public CharacterApiDataSource(CharacterProperties characterProperties, RestOperations restOperations) {
        this.characterProperties = characterProperties;
        this.restOperations = restOperations;
    }

    @Override
    public CharacterEntity getCharacter(String code) {
        try {
            System.out.println(characterProperties.getCharacterURL(code));
            return restOperations.getForObject(characterProperties.getCharacterURL(code), CharacterEntity.class);
        }catch (HttpClientErrorException ex){
            System.out.println(ex.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getResponseBodyAsString());
        }

    }
}

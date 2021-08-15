package com.rickmortychallenge.configuration.character;

import com.rickmortychallenge.application.Character;
import com.rickmortychallenge.application.CharacterController;
import com.rickmortychallenge.application.data.character.datasource.CharacterApiDataSource;
import com.rickmortychallenge.application.data.character.datasource.CharacterDataSource;
import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.data.character.mapper.OriginInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.data.character.repository.CharacterApiRepository;
import com.rickmortychallenge.application.data.location.datasource.LocationApiDataSource;
import com.rickmortychallenge.application.data.location.datasource.LocationDataSource;
import com.rickmortychallenge.application.data.location.entity.LocationEntity;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.model.OriginInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;
import com.rickmortychallenge.application.domain.usecase.CharacterInfoUseCase;
import com.rickmortychallenge.common.mapper.Mapper;
import com.rickmortychallenge.configuration.location.LocationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CharacterConfiguration {

    @Bean
    Character character(CharacterInfoUseCase characterInfoUseCase) {
        return new CharacterController(characterInfoUseCase);
    }

    @Bean
    CharacterInfoUseCase characterInfoUseCase(CharacterRepository characterRepository) {
        return new CharacterInfoUseCase(characterRepository);
    }

    @Bean
    CharacterRepository characterRepository(
            CharacterDataSource characterDataSource,
            LocationDataSource locationDataSource,
            Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper,
            Mapper<OriginInfo, LocationEntity> originInfoToCharacterEntityMapper
    ) {
        return new CharacterApiRepository(
                characterDataSource,
                locationDataSource,
                characterInfoToCharacterEntityMapper,
                originInfoToCharacterEntityMapper);
    }

    @Bean
    LocationDataSource locationDataSource(LocationProperties locationProperties, RestOperations restOperations) {
        return new LocationApiDataSource(locationProperties, restOperations);
    }

    @Bean
    Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper() {
        return new CharacterInfoToCharacterEntityMapper();
    }

    @Bean
    Mapper<OriginInfo, LocationEntity> originInfoToCharacterEntityMapper() {
        return new OriginInfoToCharacterEntityMapper();
    }

    @Bean
    CharacterDataSource characterDatasource(CharacterProperties characterProperties, RestOperations restOperations) {
        return new CharacterApiDataSource(characterProperties, restOperations);
    }

    @Bean
    RestOperations restOperations() {
        return new RestTemplate();
    }

    @Bean
    CharacterProperties characterProperties() {
        return new CharacterProperties();
    }

    @Bean
    LocationProperties locationProperties() {return new LocationProperties();}
}

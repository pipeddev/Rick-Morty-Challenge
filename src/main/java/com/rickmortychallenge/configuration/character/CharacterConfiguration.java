package com.rickmortychallenge.configuration.character;

import com.rickmortychallenge.application.Character;
import com.rickmortychallenge.application.CharacterController;
import com.rickmortychallenge.application.data.character.datasource.CharacterApiDataSource;
import com.rickmortychallenge.application.data.character.datasource.CharacterDataSource;
import com.rickmortychallenge.application.data.character.entity.CharacterEntity;
import com.rickmortychallenge.application.data.character.mapper.CharacterInfoToCharacterEntityMapper;
import com.rickmortychallenge.application.data.character.repository.CharacterApiRepository;
import com.rickmortychallenge.application.domain.model.CharacterInfo;
import com.rickmortychallenge.application.domain.repository.CharacterRepository;
import com.rickmortychallenge.application.domain.usecase.CharacterInfoUseCase;
import com.rickmortychallenge.common.mapper.Mapper;
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
            //EpisodeDataSource episodeDataSource,
            Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper
            //Mapper<FirstAppearance, EpisodeEntity> firstAppearanceToEpisodeEntityMapper
    ) {
        return new CharacterApiRepository(
                characterDataSource,
                //episodeDataSource,
                characterInfoToCharacterEntityMapper
                //firstAppearanceToEpisodeEntityMapper
        );
    }


    @Bean
    Mapper<CharacterInfo, CharacterEntity> characterInfoToCharacterEntityMapper() {
        return new CharacterInfoToCharacterEntityMapper();
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
}

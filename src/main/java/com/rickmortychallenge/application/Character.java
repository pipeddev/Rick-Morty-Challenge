package com.rickmortychallenge.application;

import com.rickmortychallenge.application.domain.model.CharacterInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/character")
public interface Character {
    @GetMapping("/info/{id}")
    ResponseEntity<CharacterInfo> informationCharacter(@PathVariable String id);
}

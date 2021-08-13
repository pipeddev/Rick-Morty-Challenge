package com.rickmortychallenge.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/example")
public class ExampleController {
    @PostMapping()
    public ResponseEntity<String> informationCharacter() {
        return new ResponseEntity<>("{'message': 'Hello world'}", HttpStatus.OK);
    }
}

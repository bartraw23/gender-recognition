package io.genderrecognition.controller;

import io.genderrecognition.pojo.PersonRecognitionResult;
import io.genderrecognition.service.RecognizeGenderService;
import io.genderrecognition.service.TokensService;
import io.genderrecognition.wrapper.PersonWrapper;
import io.genderrecognition.wrapper.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RecognitionController {

    @Autowired
    private RecognizeGenderService recognizeGenderService;

    @Autowired
    private TokensService tokensService;

    @PostMapping("/name")
    public PersonRecognitionResult checkGender(@RequestBody PersonWrapper personWrapper) {
        String name = personWrapper.getName();
        return new PersonRecognitionResult(name, recognizeGenderService.recognizeGender(name, personWrapper.getByAllTokens()));
    }

    @GetMapping("/tokens")
    public Tokens getTokens() {
        return tokensService.getTokens();
    }
}

package io.genderrecognition.controller;

import io.genderrecognition.model.Gender;
import io.genderrecognition.service.RecognizeGenderService;
import io.genderrecognition.service.TokensService;
import io.genderrecognition.wrapper.PersonWrapper;
import io.genderrecognition.wrapper.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RecognitionController {

    @Autowired
    private RecognizeGenderService recognizeGenderService;

    @Autowired
    private TokensService tokensService;

    @GetMapping("/name")
    public Gender checkGender(@RequestBody PersonWrapper personWrapper) {
        return recognizeGenderService.recognizeGender(personWrapper.getName(), personWrapper.getByAllTokens());
    }

    @GetMapping("/tokens")
    public Tokens getTokens() {
        return tokensService.getTokens();
    }
}

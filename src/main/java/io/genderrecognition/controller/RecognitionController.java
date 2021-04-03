package io.genderrecognition.controller;

import io.genderrecognition.model.Gender;
import io.genderrecognition.service.BaseLoaderService;
import io.genderrecognition.service.RecognizeGenderService;
import io.genderrecognition.wrapper.PersonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RecognitionController {
    @Autowired
    private BaseLoaderService baseLoaderService;

    @Autowired
    private RecognizeGenderService recognizeGenderService;

    @GetMapping("/loadDB")
    public void loadDatabase() {
        baseLoaderService.loadNames();
    }

    @GetMapping("/name")
    public Gender checkGender(@RequestBody PersonWrapper personWrapper) {
        return recognizeGenderService.recognizeGender(personWrapper.getName(), personWrapper.getByAllTokens());
    }
}

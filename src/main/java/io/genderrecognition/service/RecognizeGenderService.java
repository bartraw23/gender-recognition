package io.genderrecognition.service;

import io.genderrecognition.model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecognizeGenderService {
    @Autowired
    private IdentifiedNameService identifiedNameService;

    @Autowired
    private FileScraper fileScraper;

    public Gender getGender(String name) {
        Gender gender = identifiedNameService.findByName(name);
        if (gender == (Gender.INCONCLUSIVE)) {
            return fileScraper.returnGender(name);
        }
        return gender;
    }
}

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


    public Gender recognizeGender(String name, boolean byAllTokens) {
        Double sum = 0D;
        String[] nameTokens = name.split(" ");
        int limit = byAllTokens ? nameTokens.length : 1;

        for (int i = 0; i < limit; i++) {
            sum += getGenderForName(nameTokens[i]).ordinal();
        }

        double avg = sum / limit;
        int round = avg > 1 ? (int) Math.ceil(avg) : (int) Math.floor(avg);

        return Gender.values()[round];
    }

    private Gender getGenderForName(String name) {
        Gender gender = identifiedNameService.findByName(name);
        if (gender == (Gender.INCONCLUSIVE)) {
            return fileScraper.returnGender(name);
        }
        return gender;
    }

}

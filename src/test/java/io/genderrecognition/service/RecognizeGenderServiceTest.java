package io.genderrecognition.service;

import io.genderrecognition.model.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RecognizeGenderServiceTest {

    @Autowired
    private RecognizeGenderService recognizeGenderService;

    @Test
    void firstTokenInconclusiveEMPTY() {
        recognitionTest("", false, Gender.INCONCLUSIVE);
    }

    @Test
    void allTokensInconclusiveEMPTY() {
        recognitionTest("", true, Gender.INCONCLUSIVE);
    }

    @Test
    void firstTokenMaleCheck() {
        recognitionTest("Jan Maria Katarzyna Rokita", false, Gender.MALE);
    }

    @Test
    void allTokensMaleCheck() {
        recognitionTest("Jan Maria Tomasz Rokita", true, Gender.MALE);
    }

    @Test
    void firstTokenFemaleCheck() {
        recognitionTest("Maria Paweł Andrzej Sławek Marcin Xiwei", false, Gender.FEMALE);
    }


    @Test
    void allTokensFemaleCheck() {
        recognitionTest("Jan Maria Elżbieta Agnieszka Rokita", true, Gender.FEMALE);
    }

    @Test
    void firstTokenInconclusive() {
        recognitionTest("Eyfjallokul", false, Gender.INCONCLUSIVE);
    }

    @Test
    void allTokensInconclusive() {
        recognitionTest("Paweł Jan Maria Anna", true, Gender.INCONCLUSIVE);
    }

    private void recognitionTest(String s, boolean b, Gender inconclusive) {
        //GIVEN
        Gender gender;
        String name = s;

        //WHEN
        gender = recognizeGenderService.recognizeGender(name, b);

        //THEN
        assertThat(gender).isEqualTo(inconclusive);
    }
}

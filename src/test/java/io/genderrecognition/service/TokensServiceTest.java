package io.genderrecognition.service;

import io.genderrecognition.wrapper.Tokens;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokensServiceTest {

    @Autowired
    private TokensService tokensService;

    @Test
    void tokensFromTestFile() {
        //GIVEN
        List<String> maleTokens = Arrays.asList("PIOTR", "KRZYSZTOF", "ANDRZEJ", "TOMASZ", "PAWEŁ", "JAN", "MICHAŁ", "MARCIN", "JAKUB", "ADAM");
        List<String> femaleTokens = Arrays.asList("ANNA", "MARIA", "KATARZYNA", "MAŁGORZATA", "AGNIESZKA", "BARBARA", "EWA", "KRYSTYNA", "MAGDALENA", "ELŻBIETA");
        Tokens tokensToBeCompared = new Tokens(maleTokens, femaleTokens);

        //WHEN
        Tokens tokens = tokensService.getTokens();

        //THEN
        assertThat(tokens.getFemaleTokens()).isEqualTo(tokensToBeCompared.getFemaleTokens());
        assertThat(tokens.getMaleTokens()).isEqualTo(tokensToBeCompared.getMaleTokens());
    }
}

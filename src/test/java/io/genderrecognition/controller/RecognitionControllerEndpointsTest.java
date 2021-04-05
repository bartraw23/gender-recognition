package io.genderrecognition.controller;

import io.genderrecognition.model.Gender;
import io.genderrecognition.wrapper.Tokens;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RecognitionControllerEndpointsTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8090";
    }

    @Test
    public void tokensEndpointTest() {
        //GIVEN
        String path = "/tokens";
        List<String> maleTokens = Arrays.asList("PIOTR", "KRZYSZTOF", "ANDRZEJ", "TOMASZ", "PAWEŁ", "JAN", "MICHAŁ", "MARCIN", "JAKUB", "ADAM");
        List<String> femaleTokens = Arrays.asList("ANNA", "MARIA", "KATARZYNA", "MAŁGORZATA", "AGNIESZKA", "BARBARA", "EWA", "KRYSTYNA", "MAGDALENA", "ELŻBIETA");
        Tokens tokensToBeCompared = new Tokens(maleTokens, femaleTokens);

        //WHEN
        Response response = given()
                .when()
                .get(path)
                .then()
                .extract()
                .response();

        List<String> maleTokensResponse = response.jsonPath().getObject("maleTokens", List.class);
        List<String> femaleTokensResponse = response.jsonPath().getObject("femaleTokens", List.class);
        Tokens tokens = new Tokens(maleTokensResponse, femaleTokensResponse);

        //THEN
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(tokens.getFemaleTokens()).isEqualTo(tokensToBeCompared.getFemaleTokens());
        assertThat(tokens.getMaleTokens()).isEqualTo(tokensToBeCompared.getMaleTokens());
    }

    @Test
    public void maleForFirstToken() {
        performTestForPostRequestToRecognizeGender("Tomasz Barbara Rokita", false, Gender.values()[0].toString());
    }

    @Test
    public void maleForAllTokens() {
        performTestForPostRequestToRecognizeGender("Jan Paweł Maria Rokita", true, Gender.values()[0].toString());
    }

    @Test
    public void femaleForFirstToken() {
        performTestForPostRequestToRecognizeGender("Elżbieta Andrzej Maria", false, Gender.values()[2].toString());
    }

    @Test
    public void femaleForAllTokens() {
        performTestForPostRequestToRecognizeGender("Jan Elżbieta Agnieszka Maria Rokita", true, Gender.values()[2].toString());
    }

    @Test
    public void emptyInconclusiveForFirstToken() {
        performTestForPostRequestToRecognizeGender("", false, Gender.values()[1].toString());
    }

    @Test
    public void emptyInconclusiveForAllTokens() {
        performTestForPostRequestToRecognizeGender("", true, Gender.values()[1].toString());
    }

    @Test
    public void inconclusiveForFirstToken() {
        performTestForPostRequestToRecognizeGender("Obelposutkalananka", false, Gender.values()[1].toString());
    }

    @Test
    public void inconclusiveForAllTokens() {
        performTestForPostRequestToRecognizeGender("Jan Maria Rokita", true, Gender.values()[1].toString());
    }

    private void performTestForPostRequestToRecognizeGender(String name, Boolean boolByAlltokens, String expected) {
        //GIVEN
        String byAllTokens = boolByAlltokens ? "true" : "false";
        String requestBody = String.format("{ \"name\": \"%s\",\"byAllTokens\": %s }", name, byAllTokens);
        String path = "/name";

        //WHEN
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post(path)
                .then()
                .extract().response();

        //THEN
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("gender")).isEqualTo(expected);
    }
}

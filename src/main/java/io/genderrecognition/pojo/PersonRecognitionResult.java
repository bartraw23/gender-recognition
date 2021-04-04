package io.genderrecognition.pojo;

import io.genderrecognition.model.Gender;

public class PersonRecognitionResult {
    private String name;
    private Gender gender;

    public PersonRecognitionResult(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

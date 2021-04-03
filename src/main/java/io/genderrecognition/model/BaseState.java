package io.genderrecognition.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BaseState {
    @Id
    private Gender gender;
    private Boolean uploaded;

    public BaseState(Gender gender, Boolean uploaded) {
        this.gender = gender;
        this.uploaded = uploaded;
    }

    public BaseState() {

    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }
}

package io.genderrecognition.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdentifiedName {
    @Id
    private String name;

    @Column
    private Gender gender;

    public IdentifiedName(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public IdentifiedName() {

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

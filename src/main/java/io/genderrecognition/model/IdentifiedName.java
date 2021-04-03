package io.genderrecognition.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdentifiedName {
    private String name;
    private Gender gender;
    private Long id;

    public IdentifiedName(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public IdentifiedName() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
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

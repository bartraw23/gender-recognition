package io.genderrecognition.wrapper;

public class PersonWrapper {
    private String name;
    private Boolean byAllTokens;

    public PersonWrapper(String name, Boolean byAllTokens) {
        this.name = name;
        this.byAllTokens = byAllTokens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getByAllTokens() {
        return byAllTokens;
    }

    public void setByAllTokens(Boolean byAllTokens) {
        this.byAllTokens = byAllTokens;
    }
}

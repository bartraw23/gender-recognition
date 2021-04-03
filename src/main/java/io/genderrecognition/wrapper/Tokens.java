package io.genderrecognition.wrapper;

import java.util.List;

public class Tokens {
    List<String> maleTokens;
    List<String> femaleTokens;

    public Tokens(List<String> maleTokens, List<String> femaleTokens) {
        this.maleTokens = maleTokens;
        this.femaleTokens = femaleTokens;
    }

    public List<String> getMaleTokens() {
        return maleTokens;
    }

    public void setMaleTokens(List<String> maleTokens) {
        this.maleTokens = maleTokens;
    }

    public List<String> getFemaleTokens() {
        return femaleTokens;
    }

    public void setFemaleTokens(List<String> femaleTokens) {
        this.femaleTokens = femaleTokens;
    }
}

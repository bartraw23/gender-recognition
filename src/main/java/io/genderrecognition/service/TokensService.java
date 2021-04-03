package io.genderrecognition.service;

import io.genderrecognition.model.BaseState;
import io.genderrecognition.model.Gender;
import io.genderrecognition.repository.BaseStateRepository;
import io.genderrecognition.repository.IdentifiedNameRepository;
import io.genderrecognition.wrapper.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokensService {
    @Autowired
    IdentifiedNameRepository identifiedNameRepository;

    @Autowired
    BaseStateRepository baseStateRepository;

    @Autowired
    FileScraper fileScraper;

    public Tokens getTokens() {
        List<String> maleList = getNameList(Gender.MALE);
        List<String> femaleList = getNameList(Gender.FEMALE);
        return new Tokens(maleList, femaleList);
    }

    private List<String> getNameList(Gender gender) {
        return baseStateRepository.findByGender(gender).orElse(new BaseState(gender, false))
                .isUploaded() ? getNameListDB(gender) : getNameListFile(gender);
    }

    private List<String> getNameListDB(Gender gender) {
        return identifiedNameRepository.findByGender(gender).orElse(new ArrayList<>())
                .stream().parallel().map(in -> in.getName()).collect(Collectors.toList());
    }

    private List<String> getNameListFile(Gender gender) {
        return fileScraper.getTokensList(gender);
    }
}
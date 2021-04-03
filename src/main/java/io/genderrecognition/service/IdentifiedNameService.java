package io.genderrecognition.service;

import io.genderrecognition.model.Gender;
import io.genderrecognition.model.IdentifiedName;
import io.genderrecognition.repository.IdentifiedNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class IdentifiedNameService {
    @Autowired
    private IdentifiedNameRepository identifiedNameRepository;

    @Transactional
    public IdentifiedName addIdentifiedName(IdentifiedName identifiedName) {
        return identifiedNameRepository.save(identifiedName);
    }

    @Transactional
    public Gender findByName(String name) {
        return identifiedNameRepository.findByName(name).orElse(Gender.INCONCLUSIVE);
    }
}

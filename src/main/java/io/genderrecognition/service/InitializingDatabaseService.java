package io.genderrecognition.service;

import io.genderrecognition.model.BaseState;
import io.genderrecognition.model.Gender;
import io.genderrecognition.repository.BaseStateRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InitializingDatabaseService implements InitializingBean {

    @Autowired
    BaseStateRepository baseStateRepository;

    @Autowired
    private BaseLoaderService baseLoaderService;

    @Async
    @Override
    public void afterPropertiesSet() {
        baseStateRepository.save(new BaseState(Gender.FEMALE, false));
        baseStateRepository.save(new BaseState(Gender.MALE, false));
        baseLoaderService.execute();
    }
}
package io.genderrecognition.service;

import io.genderrecognition.model.BaseState;
import io.genderrecognition.model.Gender;
import io.genderrecognition.model.IdentifiedName;
import io.genderrecognition.repository.BaseStateRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class BaseLoaderService {
    @Autowired
    private Environment env;

    @Autowired
    private IdentifiedNameService identifiedNameService;

    @Autowired
    private BaseStateRepository baseStateRepository;

    private static Logger logger = Logger.getLogger(BaseLoaderService.class.getName());

    @Async
    public void execute() {
        loadNames("pathToMaleTokens", Gender.MALE);
        loadNames("pathToFemaleTokens", Gender.FEMALE);
    }

    private void loadNames(String pathToFemaleTokens, Gender female) {
        String femalePath = env.getProperty(pathToFemaleTokens);
        loadFromFileToDb(femalePath, female);
        baseStateRepository.save(new BaseState(female, true));
    }

    private void loadFromFileToDb(String femalePath, Gender gender) {
        try (Scanner sc = new Scanner(new File(femalePath));) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                IdentifiedName identifiedName = new IdentifiedName(line, gender);
                identifiedNameService.addIdentifiedName(identifiedName);
            }
        } catch (FileNotFoundException ex) {
            baseStateRepository.save(new BaseState(Gender.MALE, false));
            logger.error(ex.getMessage());
        }
    }
}

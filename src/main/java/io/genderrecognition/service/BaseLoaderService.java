package io.genderrecognition.service;

import io.genderrecognition.model.BaseState;
import io.genderrecognition.model.Gender;
import io.genderrecognition.model.IdentifiedName;
import io.genderrecognition.repository.BaseStateRepository;
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
    IdentifiedNameService identifiedNameService;

    @Autowired
    BaseStateRepository baseStateRepository;

    @Async
    public void loadNames() {
        String malePath = env.getProperty("pathToMaleTokens");

        try (Scanner sc = new Scanner(new File(malePath));) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                IdentifiedName identifiedName = new IdentifiedName(line, Gender.MALE);
                identifiedNameService.addIdentifiedName(identifiedName);
            }
        } catch (FileNotFoundException ex) {
            baseStateRepository.save(new BaseState(Gender.MALE, false));
        }
        baseStateRepository.save(new BaseState(Gender.MALE, true));

        String femalePath = env.getProperty("pathToFemaleTokens");

        try (Scanner sc = new Scanner(new File(femalePath));) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                IdentifiedName identifiedName = new IdentifiedName(line, Gender.FEMALE);
                identifiedNameService.addIdentifiedName(identifiedName);
            }
        } catch (FileNotFoundException ex) {
        }

        baseStateRepository.save(new BaseState(Gender.FEMALE, true));
    }
}

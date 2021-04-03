package io.genderrecognition.service;

import io.genderrecognition.model.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileScraper {
    @Autowired
    private Environment env;

    public Gender getGender(String name) {
        Gender gender;

        if (name.toUpperCase().endsWith("A")) {
            if (isGender(name, Gender.FEMALE)) {
                gender = Gender.FEMALE;
            } else {
                gender = isGender(name, Gender.MALE) ? Gender.MALE : Gender.INCONCLUSIVE;
            }
        } else {
            if (isGender(name, Gender.MALE)) {
                gender = Gender.MALE;
            } else {
                gender = isGender(name, Gender.FEMALE) ? Gender.FEMALE : Gender.INCONCLUSIVE;
            }
        }

        return gender;
    }

    public List<String> getTokensList(Gender gender) {
        String filePath = getFilePath(gender);

        List<String> tokensList = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(filePath));) {
            while (sc.hasNextLine()) {
                tokensList.add(sc.nextLine());
            }
        } catch (FileNotFoundException ex) {

        }
        return tokensList;
    }

    private boolean isGender(String name, Gender gender) {
        String filePath = getFilePath(gender);

        try (Scanner sc = new Scanner(new File(filePath));) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {

        }

        return false;
    }

    private String getFilePath(Gender gender) {
        String filePath;
        String malePath = env.getProperty("pathToMaleTokens");
        String femalePath = env.getProperty("pathToFemaleTokens");

        if (gender == Gender.FEMALE) {
            filePath = femalePath;
        } else {
            filePath = malePath;
        }
        return filePath;
    }

}

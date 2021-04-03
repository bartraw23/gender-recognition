package io.genderrecognition.service;

import io.genderrecognition.model.Gender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class FileScraper {
    private static String malePath = System.getProperty("pathToMaleTokens");
    private static String femalePath = System.getProperty("pathToFemaleTokens");

    public Gender returnGender(String name) {
        Gender gender;

        if (name.toUpperCase().endsWith("A")) {
            if (isGender(name, Gender.FEMALE)) {
                gender = Gender.FEMALE;
            } else {
                gender = isGender(name, Gender.MALE) ? Gender.MALE : Gender.UNDEFINED;
            }
        } else {
            if (isGender(name, Gender.MALE)) {
                gender = Gender.MALE;
            } else {
                gender = isGender(name, Gender.FEMALE) ? Gender.FEMALE : Gender.UNDEFINED;
            }
        }

        return gender;
    }

    private boolean isGender(String name, Gender gender) {
        String filePath;

        if (gender == Gender.FEMALE) {
            filePath = femalePath;
        } else {
            filePath = malePath;
        }

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
}

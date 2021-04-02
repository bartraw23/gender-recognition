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
            gender = checkGender(name, Gender.FEMALE) ? Gender.FEMALE : Gender.UNDEFINED;
        } else {
            gender = checkGender(name, Gender.MALE) ? Gender.MALE : Gender.UNDEFINED;
        }
        return gender;
    }

    private boolean checkGender(String name, Gender gender) {
        boolean result = false;
        String filePath;

        if (gender == Gender.FEMALE) {
            filePath = femalePath;
        } else {
            filePath = malePath;
        }

        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.toUpperCase().equals(name)) {
                    result = true;
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException ex) {

        }

        return result;
    }
}

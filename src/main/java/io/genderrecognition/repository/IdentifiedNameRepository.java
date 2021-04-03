package io.genderrecognition.repository;

import io.genderrecognition.model.Gender;
import io.genderrecognition.model.IdentifiedName;
import org.springframework.data.repository.CrudRepository;

public interface IdentifiedNameRepository extends CrudRepository<IdentifiedName, Long> {

    Gender findByName(String name);
}




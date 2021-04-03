package io.genderrecognition.repository;

import io.genderrecognition.model.Gender;
import io.genderrecognition.model.IdentifiedName;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IdentifiedNameRepository extends CrudRepository<IdentifiedName, Long> {

    Optional<Gender> findByName(String name);

    Optional<List<IdentifiedName>> findByGender(Gender gender);
}




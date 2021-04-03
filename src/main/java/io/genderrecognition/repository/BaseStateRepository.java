package io.genderrecognition.repository;

import io.genderrecognition.model.BaseState;
import io.genderrecognition.model.Gender;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BaseStateRepository extends CrudRepository<BaseState, Long> {
    Optional<BaseState> findByGender(Gender gender);
}

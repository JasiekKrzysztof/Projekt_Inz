package com.krzysztof.app.repo;

import com.krzysztof.app.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepo extends JpaRepository<Questionnaire, Long> {

}

package com.krzysztof.app.repo;

import com.krzysztof.app.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Long> {

}

package com.krzysztof.app.repo;

import com.krzysztof.app.model.Answer;
import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface odpowiedzialny za zarządzanie rekordami w tabeli
 */
@Repository
public interface AnswerRepo extends JpaRepository<Answer, Long> {

    Answer findByIdAnswer(Long id);
}

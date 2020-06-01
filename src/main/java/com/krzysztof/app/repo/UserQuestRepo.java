package com.krzysztof.app.repo;

import com.krzysztof.app.model.UserQuest;
import com.krzysztof.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Interface odpowiedzialny za zarządzanie rekordami w tabeli
 */
@Repository
public interface UserQuestRepo extends JpaRepository<UserQuest, Long> {

    List<UserQuest> findAllByQuestionnaireIdQuestionnaire (Long id);
    List<UserQuest> findAllByUsers(Users users);
}

package com.krzysztof.app.repo;

import com.krzysztof.app.model.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestRepo extends JpaRepository<UserQuest, Long> {

}

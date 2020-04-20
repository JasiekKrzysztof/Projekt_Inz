package com.krzysztof.app.repo;

import com.krzysztof.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByLogin(String login);
   // Users findUsersByLogin(String login);

}

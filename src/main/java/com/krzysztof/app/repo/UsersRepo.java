package com.krzysztof.app.repo;

import com.krzysztof.app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface odpowiedzialny za zarządzanie rekordami w tabeli
 */
@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findByLogin(String login);
    List<Users> findAllByRole(String role);
}

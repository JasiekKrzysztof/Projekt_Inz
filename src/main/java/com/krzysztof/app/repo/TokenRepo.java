package com.krzysztof.app.repo;

import com.krzysztof.app.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface odpowiedzialny za zarządzanie rekordami w tabeli
 */
@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByToken (String token);
}

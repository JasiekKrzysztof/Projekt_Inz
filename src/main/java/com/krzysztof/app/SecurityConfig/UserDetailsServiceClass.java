package com.krzysztof.app.SecurityConfig;

import com.krzysztof.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceClass implements UserDetailsService {

    /**
     * repozytorium użytkowników
     */
    private UsersRepo usersRepo;
    private String userName;

    /**
     * Konstruktor z wykorzystaniem Dependency Injection
     * @param usersRepo wstrzyknięcie zależności do interfejsu usersRepo
     */
    @Autowired
    public UserDetailsServiceClass(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    /**
     * metoda wyszukująca użytkownika po loginie w bazie danych
     * @param userLogin zmienna przechowująca login użytkownika
     * @return zwraca login użytkownika jeżeli znajduje się w on w bazie
     * @throws UsernameNotFoundException przechwytuje wyjątek
     */
    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        userName = userLogin;
        return usersRepo.findByLogin(userLogin);
    }

    /**
     * getter
     * @return zwraca login użytkownika
     */
    public String getUserName() {
        return userName;
    }
}

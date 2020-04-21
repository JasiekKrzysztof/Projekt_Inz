package com.krzysztof.app.SecurityConfig;

import com.krzysztof.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceClass implements UserDetailsService {

    private UsersRepo usersRepo;

    @Autowired
    public UserDetailsServiceClass(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {

        //Users user = usersRepo.findByLogin(userLogin);

        return usersRepo.findByLogin(userLogin);
    }
}

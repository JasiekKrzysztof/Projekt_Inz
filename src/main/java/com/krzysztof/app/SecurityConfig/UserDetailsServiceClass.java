package com.krzysztof.app.SecurityConfig;

import com.krzysztof.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceClass implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        return (UserDetails) usersRepo.findByLogin(userLogin);
    }
}

package com.krzysztof.app.view;

import com.krzysztof.app.model.Users;
import com.krzysztof.app.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartAdmin {

//    @Autowired
//    UsersRepo usersRepo;
//
//    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void addAdmin(){
//        usersRepo.save(new Users("admin", "admin@admin.pl", "admin", encoder.encode("admin123"), "ROLE_ADMIN"));
//    }

}

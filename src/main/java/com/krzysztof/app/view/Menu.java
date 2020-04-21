package com.krzysztof.app.view;

import com.krzysztof.app.model.Users;
import com.krzysztof.app.repo.UsersRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Route("")
@StyleSheet("/css/style.css")
public class Menu extends VerticalLayout {

    @Autowired
    UsersRepo usersRepo;

    TextField textFieldName = new TextField();
    TextField textFieldLogin = new TextField();
    TextField textFieldEmail = new TextField();

    Label labelHelloRegistration = new Label();

    PasswordField passwordField = new PasswordField();

    Button buttonRegistration = new Button();
    Button buttonLogin = new Button();

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    };

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Menu() {

        loginButton();
        registrationUser();

    }

    public void addUsers(){
        Users user = new Users(textFieldName.getValue(), textFieldEmail.getValue(), textFieldLogin.getValue(), encoder.encode(passwordField.getValue()), "ROLE_USER");
        //user.setPassword(encoder.encode(user.getPassword()));
        usersRepo.save(user);


//        System.out.println(usersRepo.findByLogin(user.getLogin()));
    }

    public void loginButton(){
        buttonLogin.setText("Zaloguj się");
        buttonLogin.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/login");
        });
        add(buttonLogin);
    }

    public void registrationUser(){
        labelHelloRegistration.setText("Zalogój się lub jeśli nie masz konta, stwórz je");
        textFieldName.setPlaceholder("Imię");
        textFieldLogin.setPlaceholder("Login");
        textFieldEmail.setPlaceholder("E-mail");
        passwordField.setPlaceholder("Hasło");


        buttonRegistration.setText("Zarejestruj się");

        add(labelHelloRegistration, textFieldName, textFieldEmail, textFieldLogin, passwordField, buttonRegistration);

        buttonRegistration.addClickListener(buttonClickEvent -> {
            if(passwordField.getValue().length() < 5){
                Notification.show("Hasło zbyt krótkie!");
            } else {
                addUsers();
                Notification.show("Konto zostało stworzone!");
            }
        });
    }
}

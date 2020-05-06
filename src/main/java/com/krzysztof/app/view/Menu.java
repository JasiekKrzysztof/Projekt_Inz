package com.krzysztof.app.view;

import com.krzysztof.app.SecurityConfig.Encoder;
import com.krzysztof.app.model.Users;
import com.krzysztof.app.repo.UsersRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Route("")
@StyleSheet("/css/style.css")
@Theme(value = Lumo.class, variant = Lumo.DARK)
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
    BCryptPasswordEncoder encoder = Encoder.getInstance();

   // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Menu() {
        setAlignItems(Alignment.CENTER);
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
        labelHelloRegistration.setText("Zaloguj się lub jeśli nie masz konta, stwórz je");
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

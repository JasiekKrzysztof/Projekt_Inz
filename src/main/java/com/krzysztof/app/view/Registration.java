//package com.krzysztof.app.view;
//
//import com.krzysztof.app.model.Users;
//import com.krzysztof.app.repo.UsersRepo;
//import com.vaadin.flow.component.UI;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.dependency.StyleSheet;
//import com.vaadin.flow.component.html.Label;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.progressbar.ProgressBar;
//import com.vaadin.flow.component.textfield.PasswordField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//
//
//@StyleSheet("/css/style.css")
//public class Registration extends VerticalLayout {
//
//    @Autowired
//    UsersRepo usersRepo;
//
//    TextField textFieldName = new TextField();
//    TextField textFieldLogin = new TextField();
//    TextField textFieldEmail = new TextField();
//
//    Label labelHelloRegistration = new Label();
//
//    PasswordField passwordField = new PasswordField();
//
//    Button buttonRegistration = new Button();
//    Button buttonMenu = new Button();
//
//
//    public Registration() {
//        labelHelloRegistration.setText("Witaj w rejestracji!");
//        textFieldName.setPlaceholder("Imię");
//        textFieldLogin.setPlaceholder("Login");
//        textFieldEmail.setPlaceholder("E-mail");
//        passwordField.setPlaceholder("Hasło");
//
//
//        buttonRegistration.setText("Zarejestrój się");
//
////        buttonMenu.setText("Wróć do menu głównego");
////        buttonMenu.addClickListener(buttonClickEvent -> {
////            UI.getCurrent().getPage().setLocation("http://localhost:8080");
////        });
//
//        add(labelHelloRegistration, textFieldName, textFieldEmail, textFieldLogin, passwordField, buttonRegistration, buttonMenu);
//
//        buttonRegistration.addClickListener(buttonClickEvent -> {
//            if(passwordField.getValue().length() < 5){
//                Notification.show("Hasło zbyt krótkie!");
//            } else {
//                addUsers();
//                Notification.show("Konto zostało stworzone!");
//            }
//        });
//    }
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void addUsers(){
//        Users user = new Users(textFieldName.getValue(), textFieldEmail.getValue(), textFieldLogin.getValue(), passwordField.getValue());
//        usersRepo.save(user);
//    }
//}

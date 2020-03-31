package com.krzysztof.app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("Login")
@StyleSheet("/css/style.css")
public class Login extends VerticalLayout {

    LoginForm component = new LoginForm();
    Label labelHello = new Label();
    Button buttonMenu = new Button();



    public void isAuthenticated (){

    }

    public Login() {
        component.addLoginListener(loginEvent -> {
            Notification.show("Hi! Hello!");
        });

        buttonMenu.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/Menu");
        });

        add(component, labelHello, buttonMenu);
    }

    //    component.addLoginListener(e -> {
//        boolean isAuthenticated = authenticate(e);
//        if (isAuthenticated) {
//            navigateToMainPage();
//        } else {
//            component.setError(true);
//        }
//    });
}

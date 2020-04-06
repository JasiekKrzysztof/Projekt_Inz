package com.krzysztof.app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;

import javax.servlet.http.HttpSession;

@Route("Menu")
@StyleSheet("/css/style.css")
public class Menu extends HorizontalLayout {

    Button buttonRegistration = new Button();
    Button buttonLogin = new Button();

    public Menu() {

        buttonRegistration.setText("Załóż konto");
        buttonRegistration.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/Registration");
            

        });

        buttonLogin.setText("Zaloguj się");
        buttonRegistration.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/Login");
        });


        add(buttonRegistration, buttonLogin);
    }
}

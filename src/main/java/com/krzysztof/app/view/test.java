package com.krzysztof.app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class test extends VerticalLayout {

    Button buttonRegistration = new Button();
    Button buttonLogin = new Button();

    @PutMapping("/test")
    public void sayHello(){
        buttonRegistration.setText("Załóż konto");
        buttonRegistration.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/Registration");


        });

        buttonLogin.setText("Zaloguj się");
        buttonRegistration.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/login");
        });


        add(buttonRegistration, buttonLogin);
    }

}

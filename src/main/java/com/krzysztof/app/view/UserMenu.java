package com.krzysztof.app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("api/user-menu")
public class UserMenu extends HorizontalLayout {

    Button addNewQuestButton = new Button();
    Button viewQuestButton = new Button();

    public UserMenu() {

        setAddNewQuestButton();
        setViewQuestButton();

    }

    public void setAddNewQuestButton(){
        addNewQuestButton.setText("Dodaj nową ankietę");

        addNewQuestButton.addClickListener(buttonClickEvent -> {
           UI.getCurrent().getPage().setLocation("/api/new-questionnaire");
        });
        add(addNewQuestButton);
    }

    public void setViewQuestButton(){
        viewQuestButton.setText("Przeglądaj ankiety");

        viewQuestButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/api/view-questionnaire");
        });
        add(viewQuestButton);
    }

}

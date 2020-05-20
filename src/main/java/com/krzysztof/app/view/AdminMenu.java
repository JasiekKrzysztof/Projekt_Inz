package com.krzysztof.app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("admin")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class AdminMenu extends HorizontalLayout {

    Button addNewQuestButton = new Button();
    Button viewQuestButton = new Button();
    Button showAnswerButton = new Button();

    public AdminMenu() {

        setAlignItems(Alignment.CENTER);
        setAddNewQuestButton();
        setViewQuestButton();
        setShowAnswerButton();

    }

    public void setAddNewQuestButton(){
        addNewQuestButton.setText("Dodaj nową ankietę");

        addNewQuestButton.addClickListener(buttonClickEvent -> {
           UI.getCurrent().getPage().setLocation("/admin/new-questionnaire");
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

    public void setShowAnswerButton(){
        showAnswerButton.setText("Przeglądaj odpowiedzi");

        showAnswerButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/admin/questionnairs");
        });
        add(showAnswerButton);
    }

}

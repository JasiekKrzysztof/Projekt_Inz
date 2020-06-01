package com.krzysztof.app.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("admin")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class AdminMenu extends VerticalLayout {

    /**
     * przycisk "dodawanie ankiet"
     */
    Button addNewQuestButton = new Button();
    /**
     * przycisk "wyświetlanie ankiet"
     */
    Button viewQuestButton = new Button();
    /**
     * przycisk "wyświetlanie statystyk"
     */
    Button showAnswerButton = new Button();

    /**
     * konstruktor klasy
     */
    public AdminMenu() {

        setAlignItems(Alignment.CENTER);
        setAddNewQuestButton();
        setViewQuestButton();
        setShowAnswerButton();

    }

    /**
     * metoda przenosząca na stronę umożliwiającą dodawnie nowych ankiet przez admina
     */
    public void setAddNewQuestButton(){
        addNewQuestButton.setText("Dodaj nową ankietę");

        addNewQuestButton.addClickListener(buttonClickEvent -> {
           UI.getCurrent().getPage().setLocation("/admin/new-questionnaire");
        });
        add(addNewQuestButton);
    }

    /**
     * metoda przenosząca na stronę umożliwiającą wyswietlanie poszczególnych ankiet
     */
    public void setViewQuestButton(){
        viewQuestButton.setText("Przeglądaj ankiety");

        viewQuestButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/api/view-questionnaire");
        });
        add(viewQuestButton);
    }

    /**
     * metoda przenosząca na stronę umożliwiającą wyświetlanie statystyk o danej ankiecie
     */
    public void setShowAnswerButton(){
        showAnswerButton.setText("Przeglądaj odpowiedzi");

        showAnswerButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/admin/questionnairs");
        });
        add(showAnswerButton);
    }

}
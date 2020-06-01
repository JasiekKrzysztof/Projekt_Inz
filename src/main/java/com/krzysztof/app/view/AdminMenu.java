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
        logoutButton();
        setAddNewQuestButton();
        setShowAnswerButton();

    }

    /**
     * metoda pozwalająca na wyglowanie się
     */
    public void logoutButton(){
        Button logoutButton = new Button("Wyloguj się");
        logoutButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/logout");
            UI.getCurrent().getPage().setLocation("http://localhost:8080");
        });
        add(logoutButton);
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
     * metoda przenosząca na stronę umożliwiającą wyświetlanie statystyk o danej ankiecie
     */
    public void setShowAnswerButton(){
        showAnswerButton.setText("Przeglądaj statystykę");

        showAnswerButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/admin/questionnairs");
        });
        add(showAnswerButton);
    }

}

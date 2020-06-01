package com.krzysztof.app.view;

import com.krzysztof.app.model.Answer;
import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.model.UserQuest;
import com.krzysztof.app.repo.AnswerRepo;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.krzysztof.app.repo.UserQuestRepo;
import com.krzysztof.app.repo.UsersRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.util.*;

@Route("admin/questionnairs")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class PercentOfGivenQuestionnaire extends VerticalLayout {

    /**
     * Repozytorium pytań
     */
    QuestionnaireRepo questionnaireRepo;
    /**
     * Repozytorium poszczegółnych użytkowników i rozwiązanych ankiet przez nich
     */
    UserQuestRepo userQuestRepo;
    /**
     * Repozytorium użytkowników
     */
    UsersRepo usersRepo;
    /**
     * Widok pytań oraz przycisków przenoszących do poszczególnych ankiet
     */
    Grid<Questionnaire> statisticsGrid;
    Button goBack = new Button("Powrót do menu");

    /**
     * Konstruktor z wykorzystaniem Dependency Injection
     * @param userQuestRepo wstrzyknięcie zależności do interfejsu userQuestRepo
     * @param questionnaireRepo wstrzyknięcie zależności do interfejsu questionnaireRepo
     * @param usersRepo wstrzyknięcie zależności do interfejsu usersRepo
     */
    public PercentOfGivenQuestionnaire(UserQuestRepo userQuestRepo, QuestionnaireRepo questionnaireRepo, UsersRepo usersRepo) {
        this.questionnaireRepo = questionnaireRepo;
        this.userQuestRepo = userQuestRepo;
        this.usersRepo = usersRepo;
        this.statisticsGrid = new Grid<>(Questionnaire.class);

        setAlignItems(Alignment.CENTER);

        showStatistics();
    }

    /**
     * Metoda wyświetlająca statystyki danej ankiety, pokazuje ile osób rozwiązało daną ankietę,
     * oraz kto rozwiązał
     */
    public void showStatistics(){
        statisticsGrid.setItems(questionnaireRepo.findAll());
        statisticsGrid.setColumns("name");

        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());

        statisticsGrid.addComponentColumn(questionnaire -> {
            Button resolvedButton = new Button("Sprawdź kto wypełnił");
            resolvedButton.addClickListener(e -> {
                Dialog statisticsDialog = new Dialog();
                VerticalLayout verticalLayout = new VerticalLayout();

                Integer allUserInDatabase = usersRepo.findAllByRole("ROLE_USER").size();
                Integer numberOffUserAnswerQuest = userQuestRepo.findAllByQuestionnaireIdQuestionnaire(questionnaire.getIdQuestionnaire()).size();
                verticalLayout.add(new Label("Wypełniło: " + numberOffUserAnswerQuest + "/" + allUserInDatabase + " osób"));
                String nameUser;
                for (int i=0; i<numberOffUserAnswerQuest; i++) {
                    nameUser = userQuestRepo.findAllByQuestionnaireIdQuestionnaire(questionnaire.getIdQuestionnaire()).get(i).getUsers().getName();
                    verticalLayout.add(new Label(nameUser));
                }
                statisticsDialog.add(verticalLayout);
                statisticsDialog.open();
            });
            editButtons.add(resolvedButton);
            return resolvedButton;
        });
        add(statisticsGrid);
        goBack.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/admin");
        });
        add(goBack);
    }

}
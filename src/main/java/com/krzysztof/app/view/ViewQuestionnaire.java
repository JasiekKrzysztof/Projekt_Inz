package com.krzysztof.app.view;

import com.krzysztof.app.SecurityConfig.UserDetailsServiceClass;
import com.krzysztof.app.model.Answer;
import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.pattern.Iterator;
import com.krzysztof.app.repo.AnswerRepo;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.krzysztof.app.repo.UsersRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.*;
import java.util.List;

@Route("api/view-questionnaire")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class ViewQuestionnaire extends VerticalLayout {

    QuestionnaireRepo questionnaireRepo;
    AnswerRepo answerRepo;
    UsersRepo usersRepo;
    Grid<Questionnaire> questionnaireGrid;
    Grid<Answer> answerGrid;
    UserDetailsServiceClass userDetailsServiceClass;
    List<String> questList = new ArrayList<>();

    @Autowired
    public ViewQuestionnaire(QuestionnaireRepo questionnaireRepo, UsersRepo usersRepo, AnswerRepo answerRepo, UserDetailsServiceClass userDetailsServiceClass) {
        this.questionnaireGrid = new Grid<>(Questionnaire.class);
        this.answerGrid = new Grid<>(Answer.class);
        this.questionnaireRepo = questionnaireRepo;
        this.usersRepo = usersRepo;
        this.answerRepo = answerRepo;
        this.userDetailsServiceClass = userDetailsServiceClass;
        setAlignItems(Alignment.CENTER);
        showQuestionnaire();
        completedSurveys(userDetailsServiceClass.getUserName());
    }

    public void showQuestionnaire(){
        questionnaireGrid.setItems(questionnaireRepo.findAll());
        questionnaireGrid.setColumns("name", "date");

        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());

        questionnaireGrid.addComponentColumn(questionnaire -> {
            Button resolvedButton = new Button("Wypełnij ankietę");
            resolvedButton.addClickListener(e -> {
                questionnaireGrid.setVisible(false);
                answerGrid.setVisible(false);

                questList.add(questionnaire.getQuestion1());
                questList.add(questionnaire.getQuestion2());
                questList.add(questionnaire.getQuestion3());
                questList.add(questionnaire.getQuestion4());
                questList.add(questionnaire.getQuestion5());
                questList.add(questionnaire.getQuestion6());
                questList.add(questionnaire.getQuestion7());
                questList.add(questionnaire.getQuestion8());
                questList.add(questionnaire.getQuestion9());
                questList.add(questionnaire.getQuestion10());

//                System.out.println(userDetailsServiceClass.getUserName());

                resolveQuest(questionnaire.getIdQuestionnaire(), userDetailsServiceClass.getUserName());



            });
            editButtons.add(resolvedButton);
            return resolvedButton;
        });

        add(questionnaireGrid);
    }

    public void resolveQuest(Long idQuestionnaire, String userName){
        List<String> answerList = new ArrayList<>(5);
        answerList.add("1");
        answerList.add("2");
        answerList.add("3");
        answerList.add("4");
        answerList.add("5");

        List <ComboBox> comboBoxList = new ArrayList<>();

        for(int i=0; i<questList.size(); i++){
            if (questList.get(i) != "") {
                ComboBox answers = new ComboBox();
                answers.setItems(answerList);
                comboBoxList.add(answers);
            }
        }

        int i=0;
        Iterator iterator = new Iterator(questList);
        while (iterator.hasNext()) {
            if(iterator.Next() != ""){
                add(new Label(iterator.currentItem()));
                add(comboBoxList.get(i));
            }
            i++;
        }

//        for(int i=0; i<10; i++){
//            if (questList.get(i) != "") {
//                add(new Label(questList.get(i)));
//                add(comboBoxList.get(i));
//            }
//        }





        Button saveButton = new Button("Zapisz opdowiedzi");
        saveButton.addClickListener(buttonClickEvent -> {
//            add(new Label(setAnswer1.getValue().toString()));
            Notification.show("Odpowiedzi zostały zapisane").setPosition(Notification.Position.MIDDLE);
            String []getAnswer = {"0","0","0","0","0","0","0","0","0","0"};

            for(int j=0; j<comboBoxList.size(); j++){
                getAnswer[j] = comboBoxList.get(j).getValue().toString();
            }
            Answer answer = new Answer(questionnaireRepo.findById(idQuestionnaire).get(), usersRepo.findByLogin(userName), getAnswer[0], getAnswer[1], getAnswer[2], getAnswer[3], getAnswer[4], getAnswer[5], getAnswer[6], getAnswer[7], getAnswer[8], getAnswer[9]);
            answerRepo.save(answer);
            UI.getCurrent().getPage().setLocation("/api/view-questionnaire");
        });
        add(saveButton);

    }

    private void completedSurveys(String userName){
        answerGrid.setItems(answerRepo.findAllByUsers(usersRepo.findByLogin(userName)));
        answerGrid.setColumns("answer1");

        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());

        answerGrid.addComponentColumn(questionnaire -> {
            Button showButton = new Button("Sprawdź odpowiedzi");
            showButton.addClickListener(e -> {
                answerGrid.setVisible(false);
                questionnaireGrid.setVisible(false);

                });
            editButtons.add(showButton);
            return showButton;
        });

        add(answerGrid);
    }

}

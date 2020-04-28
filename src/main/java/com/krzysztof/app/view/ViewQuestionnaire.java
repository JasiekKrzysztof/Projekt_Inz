package com.krzysztof.app.view;

import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Route("api/view-questionnaire")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class ViewQuestionnaire extends VerticalLayout {

    QuestionnaireRepo questionnaireRepo;
    Grid<Questionnaire> questionnaireGrid;

    List<String> questList = new ArrayList<>(10);

    @Autowired
    public ViewQuestionnaire(QuestionnaireRepo questionnaireRepo) {
        this.questionnaireGrid = new Grid<>(Questionnaire.class);
        this.questionnaireRepo = questionnaireRepo;
        setAlignItems(Alignment.CENTER);
        showQuestionnaire();
    }

    private void showQuestionnaire(){
        questionnaireGrid.setItems(questionnaireRepo.findAll());
        questionnaireGrid.setColumns("name", "date");

        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());

        questionnaireGrid.addComponentColumn(questionnaire -> {
            Button resolvedButton = new Button("Wypełnij ankietę");
            resolvedButton.addClickListener(e -> {
//                UI.getCurrent().getPage().setLocation("/api/view-questionnaire/resolve");
                questionnaireGrid.setVisible(false);

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

                resolveQuest();
            });
            editButtons.add(resolvedButton);
            return resolvedButton;
        });

        add(questionnaireGrid);
    }

    private void resolveQuest(){
        List<String> answerList = new ArrayList<>(5);
        answerList.add("1-Źle");
        answerList.add("2-Może być");
        answerList.add("3-Obojętnie");
        answerList.add("4-Dobrze");
        answerList.add("5-Super");

        List <ComboBox> comboBoxList = new ArrayList<>(10);

        ComboBox setAnswer1= new ComboBox();
        setAnswer1.setItems(answerList);
        comboBoxList.add(setAnswer1);
        ComboBox setAnswer2= new ComboBox();
        setAnswer2.setItems(answerList);
        comboBoxList.add(setAnswer2);
        ComboBox setAnswer3= new ComboBox();
        setAnswer3.setItems(answerList);
        comboBoxList.add(setAnswer3);
        ComboBox setAnswer4= new ComboBox();
        setAnswer4.setItems(answerList);
        comboBoxList.add(setAnswer4);
        ComboBox setAnswer5= new ComboBox();
        setAnswer5.setItems(answerList);
        comboBoxList.add(setAnswer5);
        ComboBox setAnswer6= new ComboBox();
        setAnswer6.setItems(answerList);
        comboBoxList.add(setAnswer6);
        ComboBox setAnswer7= new ComboBox();
        setAnswer7.setItems(answerList);
        comboBoxList.add(setAnswer7);
        ComboBox setAnswer8= new ComboBox();
        setAnswer8.setItems(answerList);
        comboBoxList.add(setAnswer8);
        ComboBox setAnswer9= new ComboBox();
        setAnswer9.setItems(answerList);
        comboBoxList.add(setAnswer9);
        ComboBox setAnswer10= new ComboBox();
        setAnswer10.setItems(answerList);
        comboBoxList.add(setAnswer10);


        for(int i=0; i<10; i++){
            if (questList.get(i) != "") {
                add(new Label(questList.get(i)));
                add(comboBoxList.get(i));
            }
        }

        Button saveButton = new Button("Zapisz opdowiedzi");
        saveButton.addClickListener(buttonClickEvent -> {
//            add(new Label(setAnswer1.getValue().toString()));
            UI.getCurrent().getPage().setLocation("/api/view-questionnaire");
        });
        add(saveButton);

    }
}

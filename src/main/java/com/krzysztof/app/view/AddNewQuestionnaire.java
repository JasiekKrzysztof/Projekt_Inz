package com.krzysztof.app.view;

import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.model.Users;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Route("api/new-questionnaire")
public class AddNewQuestionnaire extends VerticalLayout {

    @Autowired
    QuestionnaireRepo questionnaireRepo;

    private NumberField numberField = new NumberField("Wybierz liczbę pytań w ankiecie i przejdź dalej");

    TextField titleTextField = new TextField("Podaj tytuł ankiety:");

    private Button nextButton = new Button("Dalej");
    private Button endButton = new Button("Zapisz i zakończ");
    private Button goToMenuButton = new Button("Wróć do menu");

    private TextField questionTextField1 = new TextField("Pytanie 1");
    private TextField questionTextField2 = new TextField("Pytanie 2");
    private TextField questionTextField3 = new TextField("Pytanie 3");
    private TextField questionTextField4 = new TextField("Pytanie 4");
    private TextField questionTextField5 = new TextField("Pytanie 5");
    private TextField questionTextField6 = new TextField("Pytanie 6");
    private TextField questionTextField7 = new TextField("Pytanie 7");
    private TextField questionTextField8 = new TextField("Pytanie 8");
    private TextField questionTextField9 = new TextField("Pytanie 9");
    private TextField questionTextField10 = new TextField("Pytanie 10");
    private  List<TextField> textList = new ArrayList<TextField>();

    public AddNewQuestionnaire() {

        add(titleTextField, numberField, nextButton);
        setNumberField();
        nextButton.addClickListener(buttonClickEvent -> {
            numberField.setVisible(false);
            nextButton.setVisible(false);
            titleTextField.setVisible(false);
            createQuestionnaire();
        });
    }

    private void setNumberField(){
        numberField.setValue(1d);
        numberField.setHasControls(true);
        numberField.setMin(1);
        numberField.setMax(10);
    }

    private void createQuestionnaire(){
        textList.add(questionTextField1);
        textList.add(questionTextField2);
        textList.add(questionTextField3);
        textList.add(questionTextField4);
        textList.add(questionTextField5);
        textList.add(questionTextField6);
        textList.add(questionTextField7);
        textList.add(questionTextField8);
        textList.add(questionTextField9);
        textList.add(questionTextField10);

        for (int i=0; i<=numberField.getValue()-1; i++){
            add(textList.get(i));
        }
        //add(questionTextField1, questionTextField2, questionTextField3, questionTextField4, questionTextField5, questionTextField6, questionTextField7, questionTextField8, questionTextField9, questionTextField10);
        setEndButton();
        setGoToMenuButton();

    }


    private void setEndButton(){
        add(endButton);

        endButton.addClickListener(buttonClickEvent -> {
            Date date = new Date();

            Questionnaire questionnaire = new Questionnaire(titleTextField.getValue(), date, questionTextField1.getValue(), questionTextField2.getValue(),questionTextField3.getValue(), questionTextField4.getValue(), questionTextField5.getValue(), questionTextField6.getValue(), questionTextField7.getValue(), questionTextField8.getValue(), questionTextField9.getValue(), questionTextField10.getValue());
            questionnaireRepo.save(questionnaire);
        });
    }

    private void setGoToMenuButton(){
        add(goToMenuButton);

        goToMenuButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("/api/user-menu");
        });
    }

}

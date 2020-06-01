package com.krzysztof.app.view;

import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Route("admin/new-questionnaire")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class AddNewQuestionnaire extends VerticalLayout {

    /**
     * repozytorium pytań
     */
    @Autowired
    QuestionnaireRepo questionnaireRepo;

    /**
     * pole umożliwiające wybór liczby pytań
     */
    private NumberField numberField = new NumberField("Wybierz liczbę pytań w ankiecie i przejdź dalej");
    /**
     * pole do wpisania tytułu ankiety
     */
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

    /**
     * konstruktor klasy wywołujący kolejne metody
     */
    public AddNewQuestionnaire() {
        setAlignItems(Alignment.CENTER);
        add(titleTextField, numberField, nextButton);
        setNumberField();
        nextButton.addClickListener(buttonClickEvent -> {
            numberField.setVisible(false);
            nextButton.setVisible(false);
            titleTextField.setVisible(false);
            createQuestionnaire();
        });
    }

    /**
     * metoda służąca do ustawienia liczby pytań
     */
    private void setNumberField(){
        numberField.setValue(1d);
        numberField.setHasControls(true);
        numberField.setMin(1);
        numberField.setMax(10);
    }

    /**
     * metoda służąca do tworzenia nowej Ankiety, dodawanie kolejnych pytań
     */
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
        setEndButton();
    }

    /**
     * metoda służąca do zapisania pytań do ankiety w bazie danych
     */
    private void setEndButton(){
        add(endButton);

        endButton.addClickListener(buttonClickEvent -> {
            Date date = new Date();

            Notification.show("Ankieta została dodana pomyślnie!").setPosition(Notification.Position.MIDDLE);

            Questionnaire questionnaire = Questionnaire.builder()
                    .name(titleTextField.getValue())
                    .date(date)
                    .question1(questionTextField1.getValue())
                    .question2(questionTextField2.getValue())
                    .question3(questionTextField3.getValue())
                    .question4(questionTextField4.getValue())
                    .question5(questionTextField5.getValue())
                    .question6(questionTextField6.getValue())
                    .question7(questionTextField7.getValue())
                    .question8(questionTextField8.getValue())
                    .question9(questionTextField9.getValue())
                    .question10(questionTextField10.getValue())
                    .build();
            questionnaireRepo.save(questionnaire);
            Button backToMenu = new Button("Wróć do menu");
            backToMenu.addClickListener(buttonClickEvent1 -> {
                UI.getCurrent().getPage().setLocation("http://localhost:8080/admin");
            });
            add(backToMenu);
        });
    }

}

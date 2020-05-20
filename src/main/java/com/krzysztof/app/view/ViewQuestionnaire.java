package com.krzysztof.app.view;

import com.krzysztof.app.SecurityConfig.Encoder;
import com.krzysztof.app.SecurityConfig.UserDetailsServiceClass;
import com.krzysztof.app.model.Answer;
import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.model.Token;
import com.krzysztof.app.model.UserQuest;
import com.krzysztof.app.pattern.Iterator;
import com.krzysztof.app.repo.*;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.List;

@Route("api/view-questionnaire")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class ViewQuestionnaire extends VerticalLayout {

    QuestionnaireRepo questionnaireRepo;
    AnswerRepo answerRepo;
    UsersRepo usersRepo;
    TokenRepo tokenRepo;
    UserQuestRepo userQuestRepo;
    Grid<Questionnaire> questionnaireGrid;
    Grid<Answer> answerGrid;
    UserDetailsServiceClass userDetailsServiceClass;
    List<String> questList = new ArrayList<>();
    Button checkAnswerButton;
    Label infoTokenLabel;
    TextField enterTokenTextField;
    CheckAnswer checkAnswer;

    @Autowired
    public ViewQuestionnaire(QuestionnaireRepo questionnaireRepo, UsersRepo usersRepo, AnswerRepo answerRepo, TokenRepo tokenRepo,
                             UserQuestRepo userQuestRepo, UserDetailsServiceClass userDetailsServiceClass, CheckAnswer checkAnswer) {
        this.questionnaireGrid = new Grid<>(Questionnaire.class);
        this.answerGrid = new Grid<>(Answer.class);
        this.questionnaireRepo = questionnaireRepo;
        this.usersRepo = usersRepo;
        this.answerRepo = answerRepo;
        this.tokenRepo = tokenRepo;
        this.userQuestRepo = userQuestRepo;
        this.userDetailsServiceClass = userDetailsServiceClass;
        this.checkAnswer = checkAnswer;

        setAlignItems(Alignment.CENTER);

        logoutButton();
        showQuestionnaire();
        showCheckAnswerButton();
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
                checkAnswerButton.setVisible(false);
                infoTokenLabel.setVisible(false);
                enterTokenTextField.setVisible(false);

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


        Button saveButton = new Button("Zapisz opdowiedzi");
        saveButton.addClickListener(buttonClickEvent -> {
//            add(new Label(setAnswer1.getValue().toString()));
            Notification.show("Odpowiedzi zostały zapisane").setPosition(Notification.Position.MIDDLE);
            String []getAnswer = {"0","0","0","0","0","0","0","0","0","0"};

            for(int j=0; j<comboBoxList.size(); j++){
                getAnswer[j] = comboBoxList.get(j).getValue().toString();
            }
            Answer answer = new Answer(questionnaireRepo.findById(idQuestionnaire).get(), getAnswer[0], getAnswer[1], getAnswer[2], getAnswer[3],
                                        getAnswer[4], getAnswer[5], getAnswer[6], getAnswer[7], getAnswer[8], getAnswer[9]);
            answerRepo.save(answer);

            String hashToken = generateTokn(userName);
            Token token = new Token(answerRepo.findByIdAnswer(answer.getIdAnswer()), hashToken);
            tokenRepo.save(token);

            UserQuest userQuest = new UserQuest(usersRepo.findByLogin(userName),
                                                questionnaireRepo.findById(idQuestionnaire).get());
            userQuestRepo.save(userQuest);


            VerticalLayout verticalLayout = new VerticalLayout();
            Dialog tokenDialog = new Dialog();
            verticalLayout.add(new Label("Zapisz sobie token dzięki któremu możesz sprawdzić odpowiedzi na ankietę"));
            verticalLayout.add(new Label(hashToken));
            Button closeTokenDialogButton = new Button("Wróć do menu");
            closeTokenDialogButton.addClickListener(buttonClickEvent1 -> {
                UI.getCurrent().getPage().setLocation("/api/view-questionnaire");
            });
            verticalLayout.add(closeTokenDialogButton);
            tokenDialog.add(verticalLayout);
            tokenDialog.open();
        });
        add(saveButton);

    }

    private String generateTokn(String userName){
        String tokenHash = new Date().toString() + userName;

        return Encoder.getInstance().encode(tokenHash);
    }

    private void showCheckAnswerButton(){
        checkAnswerButton = new Button("Sprawdź odpowiedzi");
        infoTokenLabel = new Label("Podaj token ankiety, której chcesz zobaczyć swoje odpowiedzi");
        enterTokenTextField = new TextField("Token");
        checkAnswerButton.addClickListener(buttonClickEvent -> {
            checkAnswer.showAnswers(enterTokenTextField.getValue());
        });
        add(infoTokenLabel, enterTokenTextField, checkAnswerButton);
    }

    public void logoutButton(){
        Button logoutButton = new Button("Wyloguj się");
        logoutButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/logout");
            UI.getCurrent().getPage().setLocation("http://localhost:8080");
        });
        add(logoutButton);
    }
}

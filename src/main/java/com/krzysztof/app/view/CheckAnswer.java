package com.krzysztof.app.view;

import com.krzysztof.app.model.Answer;
import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.model.Token;
import com.krzysztof.app.repo.AnswerRepo;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.krzysztof.app.repo.TokenRepo;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckAnswer {

    TokenRepo tokenRepo;

    @Autowired
    public CheckAnswer(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    public void showAnswers(String token){
        String name = tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getName();
        Label questName = new Label("Tytuł ankiety: " + name);

        List<String> showQuestList = new ArrayList<>();
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion1());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion2());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion3());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion4());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion5());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion6());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion7());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion8());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion9());
        showQuestList.add(tokenRepo.findByToken(token).getAnswer().getQuestionnaire().getQuestion10());

        List<String> showAnswerList = new ArrayList<>();
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer1());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer2());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer3());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer4());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer5());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer6());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer7());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer8());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer9());
        showAnswerList.add(tokenRepo.findByToken(token).getAnswer().getAnswer10());

        Dialog questDialog = new Dialog();
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.add(questName);
        for (int i = 0; i<showQuestList.size(); i++){
            if (showQuestList.get(i) != ""){
                verticalLayout.add(new Label(showQuestList.get(i)));
                verticalLayout.add(new Label(showAnswerList.get(i)));
            }
        }
        questDialog.add(verticalLayout);
        questDialog.open();


    }
}

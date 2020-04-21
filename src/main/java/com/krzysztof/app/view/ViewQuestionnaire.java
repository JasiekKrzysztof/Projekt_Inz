package com.krzysztof.app.view;

import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.repo.QuestionnaireRepo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("api/view-questionnaire")
public class ViewQuestionnaire extends VerticalLayout {

    @Autowired
    QuestionnaireRepo questionnaireRepo;

    Grid<Questionnaire> questionnaireGrid = new Grid<>(Questionnaire.class);



    public ViewQuestionnaire() {
        showQuestionnaire();
    }

    public void showQuestionnaire(){
//        questionnaireGrid.setItems();
////        questionnaireGrid.removeColumnByKey("idQuestionnaire");
//        questionnaireGrid.getColumnByKey("Name");
//        questionnaireGrid.getColumnByKey("Date");
//
//
//        questionnaireGrid.setColumns("Name", "Date");
//
//        add(questionnaireGrid);
    }
}

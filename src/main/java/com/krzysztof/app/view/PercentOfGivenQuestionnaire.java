package com.krzysztof.app.view;

import com.krzysztof.app.repo.AnswerRepo;
import com.krzysztof.app.repo.UserQuestRepo;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("admin/questionnairs")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class PercentOfGivenQuestionnaire extends VerticalLayout {

    UserQuestRepo userQuestRepo;

    public PercentOfGivenQuestionnaire(UserQuestRepo userQuestRepo) {
        this.userQuestRepo = userQuestRepo;

        setAlignItems(Alignment.CENTER);

        showStatistics();
    }

    public void showStatistics(){
        
    }

}

package com.krzysztof.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuestionnaire;

    String name;
    Date date;

    public Questionnaire() {
    }

    public Questionnaire(String name, Date date){
        this.name = name;
        this.date = date;
    }

    public Long getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(Long idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

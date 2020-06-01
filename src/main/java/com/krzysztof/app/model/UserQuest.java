package com.krzysztof.app.model;

import javax.persistence.*;

@Entity
public class UserQuest {

    /**
     * id automatycznie inkrementujące
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * odniesienie sie do tabeli użytkowników
     */
    @OneToOne
    Users users;
    /**
     * odniesienie sie do tabeli ankiet
     */
    @OneToOne
    Questionnaire questionnaire;

    /**
     * konstruktor bezparametrowy
     */
    public UserQuest() {
    }

    /**
     * konstruktor
     * @param users przyjmuje odniesienie do tabeli użytkownicy
     * @param questionnaire przyjmuje odniesienie do tabeli ankiet
     */
    public UserQuest(Users users, Questionnaire questionnaire) {
        this.users = users;
        this.questionnaire = questionnaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}

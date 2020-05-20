package com.krzysztof.app.model;

import javax.persistence.*;

@Entity
public class UserQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    Users users;

    @OneToOne
    Questionnaire questionnaire;

    public UserQuest() {
    }

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

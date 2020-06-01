package com.krzysztof.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Questionnaire {

    /**
     * id automatycznie inkrementujące
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idQuestionnaire;

    String name;
    Date date;
    String question1;
    String question2;
    String question3;
    String question4;
    String question5;
    String question6;
    String question7;
    String question8;
    String question9;
    String question10;


    /**
     * konstruktor bezparametrowy
     */
    public Questionnaire() {
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

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String question6) {
        this.question6 = question6;
    }

    public String getQuestion7() {
        return question7;
    }

    public void setQuestion7(String question7) {
        this.question7 = question7;
    }

    public String getQuestion8() {
        return question8;
    }

    public void setQuestion8(String question8) {
        this.question8 = question8;
    }

    public String getQuestion9() {
        return question9;
    }

    public void setQuestion9(String question9) {
        this.question9 = question9;
    }

    public String getQuestion10() {
        return question10;
    }

    public void setQuestion10(String question10) {
        this.question10 = question10;
    }

    /**
     * metoda tworząca nowy obiekt buildera
     * @return zwraca utworzony nowy obiekt
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * statyczna, finalna klasa buidera, potrzebna do zapisania nowych obiektow w tabeli
     */
    public static final class Builder {
        String name;
        Date date;
        String question1;
        String question2;
        String question3;
        String question4;
        String question5;
        String question6;
        String question7;
        String question8;
        String question9;
        String question10;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder question1(String question1) {
            this.question1 = question1;
            return this;
        }
        public Builder question2(String question2) {
            this.question2 = question2;
            return this;
        }
        public Builder question3(String question3) {
            this.question3 = question3;
            return this;
        }
        public Builder question4(String question4) {
            this.question4 = question4;
            return this;
        }
        public Builder question5(String question5) {
            this.question5 = question5;
            return this;
        }
        public Builder question6(String question6) {
            this.question6 = question6;
            return this;
        }
        public Builder question7(String question7) {
            this.question7 = question7;
            return this;
        }
        public Builder question8(String question8) {
            this.question8 = question8;
            return this;
        }
        public Builder question9(String question9) {
            this.question9 = question9;
            return this;
        }
        public Builder question10(String question10) {
            this.question10 = question10;
            return this;
        }

        /**
         * metoda służy do budowania nowej ankiety
         * @return zwraca pytania
         */
        public Questionnaire build() {

            Questionnaire questionnaire = new Questionnaire();
            questionnaire.name = name;
            questionnaire.date = date;
            questionnaire.question1 = question1;
            questionnaire.question2 = question2;
            questionnaire.question3 = question3;
            questionnaire.question4 = question4;
            questionnaire.question5 = question5;
            questionnaire.question6 = question6;
            questionnaire.question7 = question7;
            questionnaire.question8 = question8;
            questionnaire.question9 = question9;
            questionnaire.question10 = question10;

            return questionnaire;
        }


    }

    /**
     * przeciążenie metody toString()
     * @return zwraca nazwe ankiety
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * przeciażenie metody equals()
     * @param o przyjmuje obiekt
     * @return zwraca intiger pozwalający określić różnicę w obiektach
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questionnaire that = (Questionnaire) o;
        return Objects.equals(idQuestionnaire, that.idQuestionnaire) &&
                Objects.equals(name, that.name) &&
                Objects.equals(date, that.date) &&
                Objects.equals(question1, that.question1) &&
                Objects.equals(question2, that.question2) &&
                Objects.equals(question3, that.question3) &&
                Objects.equals(question4, that.question4) &&
                Objects.equals(question5, that.question5) &&
                Objects.equals(question6, that.question6) &&
                Objects.equals(question7, that.question7) &&
                Objects.equals(question8, that.question8) &&
                Objects.equals(question9, that.question9) &&
                Objects.equals(question10, that.question10);
    }

    /**
     * przeciążenie metody hashCode()
     * @return zwraca hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(idQuestionnaire, name, date, question1, question2, question3, question4, question5, question6, question7, question8, question9, question10);
    }
}

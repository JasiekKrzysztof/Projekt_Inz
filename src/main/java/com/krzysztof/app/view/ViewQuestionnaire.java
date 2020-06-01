package com.krzysztof.app.view;

import com.krzysztof.app.SecurityConfig.Encoder;
import com.krzysztof.app.SecurityConfig.UserDetailsServiceClass;
import com.krzysztof.app.model.Answer;
import com.krzysztof.app.model.Questionnaire;
import com.krzysztof.app.model.Token;
import com.krzysztof.app.model.UserQuest;
import com.krzysztof.app.pattern.Iterator;
import com.krzysztof.app.repo.*;
import com.krzysztof.app.sender.MailService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("api/view-questionnaire")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class ViewQuestionnaire extends VerticalLayout {

    /**
     * Repozytorium pytań
     */
    QuestionnaireRepo questionnaireRepo;
    /**
     * Repozytorium odpowiedzi
     */
    AnswerRepo answerRepo;
    /**
     * Repozytorium użytkowników
     */
    UsersRepo usersRepo;
    /**
     * Repozytorium tokenów
     */
    TokenRepo tokenRepo;
    /**
     * Repozytorium użytkowników którzy rozwiązali ankiety, przechowuje ich tokeny
     */
    UserQuestRepo userQuestRepo;
    /**
     * Widok pytań
     */
    Grid<Questionnaire> questionnaireGrid;
    /**
     * Widok odpowiedzi
     */
    Grid<Answer> answerGrid;
    /**
     *  Klasa wyszukująca urzytkowników
     */
    UserDetailsServiceClass userDetailsServiceClass;
    /**
     *  Lista pytań dla danej ankiety
     */
    List<String> questList = new ArrayList<>();
    /**
     * Przycisk dający możliwość sprawdzenia odpowiedzi na daną ankietę
     */
    Button checkAnswerButton;
    /**
     * Wyświetla informacje co daje token i jak go użyć
     */
    Label infoTokenLabel;
    /**
     * Pole pozwalające wpisać token do sprawdzenia odpowiedzi na daną ankietę
     */
    TextField enterTokenTextField;
    /**
     * Odwołanie do klasy pozwalającej wyświetlić odpowiedzi na daną ankietę
     */
    CheckAnswer checkAnswer;
    /**
     * utowrzenie obiektu klasy wysyłającej e-maila
     */
    MailService mailService;

    /**
     * Konstruktor z wykorzystaniem Dependency Injection
     * @param questionnaireRepo wstrzyknięcie zależności do interfejsu questionnaireRepo
     * @param usersRepo wstrzyknięcie zależności do interfejsu usersRepo
     * @param answerRepo wstrzyknięcie zależności do interfejsu answerRepo
     * @param tokenRepo wstrzyknięcie zależności do interfejsu tokenRepo
     * @param userQuestRepo wstrzyknięcie zależności do interfejsu userQuestRepo
     * @param userDetailsServiceClass wstrzyknięcie zależności do interfejsu userDetailsServiceClass
     * @param checkAnswer stworzenie obiektu z klasy CheckAnswer
     * @param mailService stworzenie obiektu z klasy MailService
     */
    @Autowired
    public ViewQuestionnaire(QuestionnaireRepo questionnaireRepo, UsersRepo usersRepo, AnswerRepo answerRepo, TokenRepo tokenRepo,
                             UserQuestRepo userQuestRepo, UserDetailsServiceClass userDetailsServiceClass, CheckAnswer checkAnswer,
                             MailService mailService) {
        this.questionnaireGrid = new Grid<>(Questionnaire.class);
        this.answerGrid = new Grid<>(Answer.class);
        this.questionnaireRepo = questionnaireRepo;
        this.usersRepo = usersRepo;
        this.answerRepo = answerRepo;
        this.tokenRepo = tokenRepo;
        this.userQuestRepo = userQuestRepo;
        this.userDetailsServiceClass = userDetailsServiceClass;
        this.checkAnswer = checkAnswer;
        this.mailService = mailService;

        setAlignItems(Alignment.CENTER);

        adminPage();
        logoutButton();
        showQuestionnaire();
        showCheckAnswerButton();
    }

    /**
     * Metoda służąca do wyświetlania użytkownikowi ankiet, dzięki wykorzystaniu "Stream" użytkownik widzi tylko niewypełnione ankiety
     */
    public void showQuestionnaire(){
        List<UserQuest> allByUsers = userQuestRepo.findAllByUsers(usersRepo.findByLogin(userDetailsServiceClass.getUserName()));
        Stream<Questionnaire> questionnaireStream = allByUsers.stream().map(userQuest -> userQuest.getQuestionnaire());
        List<Questionnaire> collect = questionnaireStream.collect(Collectors.toList());
        List<Questionnaire> all = questionnaireRepo.findAll();
        all.removeAll(collect);
        questionnaireGrid.setItems(all);
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

                resolveQuest(questionnaire.getIdQuestionnaire(), userDetailsServiceClass.getUserName());
            });
//            if (!all.contains(questionnaire))
            editButtons.add(resolvedButton);
            return resolvedButton;
        });

        add(questionnaireGrid);
    }

    /**
     * Metoda służąca do wypełniania ankiet przez użytkownika oraz zapisywania ich do bazy danych
     * @param idQuestionnaire parametr przekazuje id użytkownika
     * @param userName parametr przekazuje nazwę użytkownika
     */
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

        /**
         * Przycisk do zapisywania odpowiedzi w bazie danych
         */
        Button saveButton = new Button("Zapisz opdowiedzi");
        saveButton.addClickListener(buttonClickEvent -> {
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

            /**
             * zmienna przechoiwuje teks wiadomości e-mail
             */
            String textToSendMail = "Twót token do sprawdzenia odpowiedzi: " + hashToken + "\n\n Twoje udzielone odpowiedzi:";
            for (int x = 0; x < getAnswer.length; x++){
                if (getAnswer[x] != "0"){
                    textToSendMail = textToSendMail + "\n\nPytanie" + (x+1) + ":\t\t\t" + questList.get(x) + "\nTwoja odpowiedź: \t" + getAnswer[x];
                }
            }


            try {
                /**
                 * wyowłanie metody wysyłającej e-mail z informacjami do użytkownika
                 */
                mailService.sendMail(usersRepo.findByLogin(userName).getEmail(),
                        "Twój token", textToSendMail, true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }


            /**
             * Wyświetlanie użytkownikowi informacji zwrotnej po zapisaniu danych z ankiety,
             * informacja ta zawiera token dzięki któremu bedzie mógł sprawdzić swoje odpowiedzi
             */
            VerticalLayout verticalLayout = new VerticalLayout();
            Dialog tokenDialog = new Dialog();
            verticalLayout.add(new Label("Token został wysłany na Twój adres e-mail, dzięki tokenowi możesz sprawdzić swoje odpowiedz" +
                    "oraz sprawdzić czy nie zostały one zmienione"));
            verticalLayout.add(new Label("Twoje odpowiedzi zostały zapisane"));
            /**
             * Przycisk powrotu do menu
             */
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

    /**
     * Metoda generujaca niepowtarzalny token
     * @param userName przekazanie nazwy użytkownika, do stworzenia niepowtarzalnego tokena
     * @return metoda zwraca token przypisany do danej ankiety oraz użytkownika
     */
    private String generateTokn(String userName){

        String tokenHash = new Date().toString() + userName;

        return Encoder.getInstance().encode(tokenHash);
    }
    /**
     * Metoda pozwalająca na sprawdzenie odpowiedzi na daną ankietę dzięki podaniu tokena, wygenerowanego podczas zapisywania odpowiedzi
     */
    private void showCheckAnswerButton(){
        checkAnswerButton = new Button("Sprawdź odpowiedzi");
        infoTokenLabel = new Label("Podaj token ankiety, której chcesz zobaczyć swoje odpowiedzi");
        enterTokenTextField = new TextField("Token");
        checkAnswerButton.addClickListener(buttonClickEvent -> {
            checkAnswer.showAnswers(enterTokenTextField.getValue());
        });
        add(infoTokenLabel, enterTokenTextField, checkAnswerButton);
    }

    /**
     * metoda zawierająca przycisk wylogowania oraz przeniesienia bezpośrednio na stronę logowania
     */
    public void logoutButton(){
        Button logoutButton = new Button("Wyloguj się");
        logoutButton.addClickListener(buttonClickEvent -> {
            UI.getCurrent().getPage().setLocation("http://localhost:8080/logout");
            UI.getCurrent().getPage().setLocation("http://localhost:8080");
        });
        add(logoutButton);
    }

    /**
     * metoda sprawdzająca czt użytkownik ma role admina, jeżeli tak to przenosi go na odpowiednią stronę dla admina
     */
    public void adminPage(){
        String userRole = usersRepo.findByLogin(userDetailsServiceClass.getUserName()).getRole();
        if (userRole.equals("ROLE_ADMIN")){
            UI.getCurrent().getPage().setLocation("http://localhost:8080/admin");
        }
    }
}

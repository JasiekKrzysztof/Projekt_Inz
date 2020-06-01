package com.krzysztof.app.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * klasa pozwalająca na wysyłanie e-maila
 */
@Service
public class MailService {

    private JavaMailSender javaMailSender;

    /**
     * konstruktor
     * @param javaMailSender wstrzyknięcie zależności
     */
    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * metoda wysyłająca e-mail
     * @param to zmienna do kogo ma być wysłany mail
     * @param subject tytuł e-maila
     * @param text temat e-maila
     * @param isHtmlContent zmienna testowa
     * @throws MessagingException przechwytywanie wyjątku
     */
    public void sendMail(String to, String subject, String text, boolean isHtmlContent) throws MessagingException {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        msg.setFrom("aplikacja.ankiety@cyberia.pl");
        javaMailSender.send(msg);
    }


}

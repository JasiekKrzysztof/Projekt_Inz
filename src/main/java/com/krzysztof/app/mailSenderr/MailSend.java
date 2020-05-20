//package com.krzysztof.app.mailSenderr;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//@Component
//public class MailSend {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void  sendEmail(String email, String token){
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(email);
//        msg.setSubject("Token");
//        msg.setText("Twój " + token );
//        javaMailSender.send(msg);
//
//
//    }
//
//}

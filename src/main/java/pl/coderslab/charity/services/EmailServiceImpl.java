package pl.coderslab.charity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendActivationMessage(String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("scaffymanager@gmail.com");
            message.setTo(to);
            message.setSubject("Activation message");
            message.setText("It's a test message");
            emailSender.send(message);
            System.out.println("mail sent");
        } catch (MailException exception) {
            exception.printStackTrace();
        }


    }
}

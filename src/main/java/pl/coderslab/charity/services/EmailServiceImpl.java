package pl.coderslab.charity.services;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender emailSender;
    private final UserService userService;

    public EmailServiceImpl(JavaMailSender emailSender, UserService userService) {
        this.emailSender = emailSender;
        this.userService = userService;
    }

    public void sendActivationMessage(String to) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("scaffymanager@gmail.com");
            message.setTo(to);
            message.setSubject("Activation message");
            String url = "localhost:8080/register/" + userService.findByEmail(to).getSecureToken().getToken();
            message.setText("Activation link = " + url);
            emailSender.send(message);
            System.out.println("mail sent");
        } catch (MailException exception) {
            exception.printStackTrace();
        }


    }
}

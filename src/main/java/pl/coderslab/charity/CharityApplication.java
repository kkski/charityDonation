package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.coderslab.charity.services.EmailService;

@SpringBootApplication
public class CharityApplication {

    public static void main(String[] args) {

        SpringApplication.run(CharityApplication.class, args);
    }

}

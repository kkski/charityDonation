package pl.coderslab.charity.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.services.EmailService;
import pl.coderslab.charity.services.UserService;
import pl.coderslab.charity.services.ValidationService;

import javax.mail.SendFailedException;
import javax.validation.Valid;
import java.util.UUID;


@Controller
public class HomeController {
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final UserService userService;
    private final EmailService emailService;
    private final ValidationService validationService;


    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, UserService userService, EmailService emailService, ValidationService validationService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.validationService = validationService;
    }

    @ModelAttribute
    public void init(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll(PageRequest.ofSize(4)).getContent());
        model.addAttribute("donationsAmount", donationRepository.count());
        model.addAttribute("bagsAmount", donationRepository.findAllQuantityAndSum().orElse(0));
    }

    @RequestMapping("/")
    public String homeAction(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userForm") UserDto userForm,
                           BindingResult bindingResult) {





        validationService.validateUser(userForm,bindingResult);

//        if (userService.findByUsername(userForm.getUsername()).getPassword().) == false) {
//            bindingResult.rejectValue("username", "username.exists",
//                    "Taki uzytkownik juz istnieje!");
//        }

        if (bindingResult.hasErrors()) {
            return "/register";
        }

        userService.saveUser(userForm);
        userService.createVerificationToken(userService.findByUsername(userForm.getUsername()));
        emailService.sendActivationMessage(userForm.getEmail());
        return "redirect:/register/after";
    }

    @GetMapping("/register/{token}")
    public String registrationConfirmation(
            @PathVariable("token") String token) {
        userService.activateUser(token);
        return "redirect:/";
    }
    @GetMapping("/register/after")
    public String registrationAfter() {
        return "registerafter";
    }

}

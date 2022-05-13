package pl.coderslab.charity;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

@Controller
@RequestMapping("/app")
public class AppController {

    private final UserService userService;
    private final InstitutionRepository institutionRepository;

    public AppController(UserService userService, InstitutionRepository institutionRepository) {
        this.userService = userService;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("")
    public String app(@AuthenticationPrincipal CurrentUser customUser,
                      Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        return "app";
    }

}

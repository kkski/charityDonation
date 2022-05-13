package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

@Controller
@RequestMapping("/app")
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/app")
    public String afterLogin(@AuthenticationPrincipal CurrentUser customUser,
                             Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByEmail(entityUser.getEmail());
        model.addAttribute("email", myUser.getEmail());
        return "/app";
    }
}
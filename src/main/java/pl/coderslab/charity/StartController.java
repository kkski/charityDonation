package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.RoleRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

import java.util.Set;

@Controller
@RequestMapping("/start")
public class StartController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public StartController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @GetMapping("")
    public String afterLogin(@AuthenticationPrincipal CurrentUser customUser,
                             Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        Role admin = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roleList = myUser.getRoles();
        if (roleList.contains(admin)) {
            return "redirect:/admin";
        }
        return "redirect:/app";
    }
}

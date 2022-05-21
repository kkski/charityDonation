package pl.coderslab.charity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
@RequestMapping("/app")
public class AppController {

    private final UserService userService;
    private final InstitutionRepository institutionRepository;
    private final UserRepository userRepository;

    public AppController(UserService userService, InstitutionRepository institutionRepository, UserRepository userRepository) {
        this.userService = userService;
        this.institutionRepository = institutionRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    void init(@AuthenticationPrincipal CurrentUser customUser,
              Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
    }
    @GetMapping("")
    public String app() {
        return "app";
    }


    @GetMapping("/profile/edit")
    public String editProfileForm(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        User userToChange = userService.findByUsername(entityUser.getUsername());
        UserDto userForm = new UserDto();
        userForm.setEmail(userToChange.getEmail());
        userForm.setFirstName(userToChange.getFirstName());
        userForm.setLastName(userToChange.getLastName());
        userForm.setUsername(userToChange.getUsername());
        model.addAttribute("userForm", userForm);
        return "editprofile";
    }

    @PostMapping("/profile/edit")
    public String doEditProfile(@Valid @ModelAttribute("userForm") UserDto userForm,
                                 BindingResult bindingResult,
                                @AuthenticationPrincipal CurrentUser customUser) {

        userService.extendValidation(userForm,bindingResult);

        if (bindingResult.hasErrors()) {
            return "editprofile";
        }

        User entityUser = customUser.getUser();
        User userToChange = userService.findByUsername(entityUser.getUsername());

        userService.updateLoggedUser(userToChange.getId(), userForm);
        return "redirect:/app";
    }

}

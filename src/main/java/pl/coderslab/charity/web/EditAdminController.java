package pl.coderslab.charity.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;
import pl.coderslab.charity.services.ValidationService;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/admins/edit/{adminId}")
public class EditAdminController {


    private final ValidationService validationService;
    private final UserService userService;
    private final UserRepository userRepository;

    public EditAdminController(ValidationService validationService, UserService userService, UserRepository userRepository) {
        this.validationService = validationService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    void init(@PathVariable Long adminId,
              @AuthenticationPrincipal CurrentUser customUser,
              Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        User userToChange = userService.findById(adminId);
        UserDto userForm = new UserDto();
        userForm.setEmail(userToChange.getEmail());
        userForm.setFirstName(userToChange.getFirstName());
        userForm.setLastName(userToChange.getLastName());
        userForm.setUsername(userToChange.getUsername());
        model.addAttribute("userForm", userForm);
        model.addAttribute("user", myUser);

    }

    @GetMapping("")
    public String editAdminForm() {

        return "app/admin/adminedit";

    }

    @PostMapping("")
    public String doEditAdmin(@Valid @ModelAttribute("userForm") UserDto userForm,
                              BindingResult bindingResult,
                              @PathVariable("adminId") Long adminId) {

        validationService.validateUser(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "app/admin/adminedit";
        }

        User userToChange = userService.findById(adminId);
        userToChange.setUsername(userForm.getUsername());
        userToChange.setEmail(userForm.getEmail());
        userToChange.setLastName(userForm.getLastName());
        userToChange.setFirstName(userForm.getFirstName());
        userToChange.setPassword(userForm.getPassword());
        userRepository.save(userToChange);
        return "redirect:/admin/admins";
    }


}

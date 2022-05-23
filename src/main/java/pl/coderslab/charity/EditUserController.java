package pl.coderslab.charity;

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
@RequestMapping("/admin/users/edit/{userId}")
public class EditUserController {

        private final ValidationService validationService;
        private final UserService userService;
        private final UserRepository userRepository;

        public EditUserController(ValidationService validationService, UserService userService, UserRepository userRepository) {
            this.validationService = validationService;
            this.userService = userService;
            this.userRepository = userRepository;
        }

        @ModelAttribute
        void init(@PathVariable Long userId,
                  @AuthenticationPrincipal CurrentUser customUser,
                  Model model) {

            User entityUser = customUser.getUser();
            User myUser = userService.findByUsername(entityUser.getUsername());
            User userToChange = userService.findById(userId);
            UserDto userForm = new UserDto();
            userForm.setEmail(userToChange.getEmail());
            userForm.setFirstName(userToChange.getFirstName());
            userForm.setLastName(userToChange.getLastName());
            userForm.setUsername(userToChange.getUsername());
            model.addAttribute("userForm", userForm);
            model.addAttribute("user", myUser);

        }

        @GetMapping("")
        public String editUserForm() {

            return "app/users/useredit";

        }

        @PostMapping("")
        public String doEditUser(@Valid @ModelAttribute("userForm") UserDto userForm,
                                  BindingResult bindingResult,
                                  @PathVariable("userId") Long userId) {

            validationService.validateUser(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "app/users/useredit";
            }

            User userToChange = userService.findById(userId);
            userToChange.setUsername(userForm.getUsername());
            userToChange.setEmail(userForm.getEmail());
            userToChange.setLastName(userForm.getLastName());
            userToChange.setFirstName(userForm.getFirstName());
            userToChange.setPassword(userForm.getPassword());
            userRepository.save(userToChange);
            return "redirect:/admin/users";
        }

    }

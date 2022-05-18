package pl.coderslab.charity;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("")
    public String admin(@AuthenticationPrincipal CurrentUser customUser,
                        Model model) {

        User entityUser = customUser.getUser();

        User myUser = userService.findByUsername(entityUser.getUsername());

        model.addAttribute("user", myUser);

        return "app/admin/admin";
    }

    @GetMapping("/admins")
    public String showAdmins(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("admins", userService.findAllWhereRoleIsAdmin());
        return "app/admin/admins";


    }

    @GetMapping("/admins/add")
    public String addAdmin(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("userForm", new UserDto());
        return "app/admin/adminadd";
    }

    @PostMapping("/admins/add")
    public String doAddAdmin(@Valid @ModelAttribute("userForm") UserDto userForm,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/register";
        }
        userService.saveAdmin(userForm);
        return "redirect:/admin/admins";
    }

    @GetMapping("/admins/edit/{adminId}")
    public String editAdminForm(@AuthenticationPrincipal CurrentUser customUser,
                                      Model model,
                                      @PathVariable("adminId") Long adminId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("userToChange", userService.findById(adminId));
        //userdto must get info from usertochange
        model.addAttribute("userForm", new UserDto());
        return "app/admin/adminedit";

    }

    @PostMapping("/admins/edit/{adminId}")
    public String doEditAdmin(@Valid @ModelAttribute("userForm") UserDto userForm,
                             BindingResult bindingResult,
                              @PathVariable("adminId") Long adminId) {

        if (bindingResult.hasErrors()) {
            return "app/institutions/institutionsedit";
        }
        User userToChange = userService.findById(adminId);
        userToChange.setUsername(userForm.getUsername());
        userToChange.setEmail(userForm.getEmail());
        userToChange.setLastName(userForm.getLastName());
        userToChange.setFirstName(userForm.getFirstName());
        userToChange.setPassword(userForm.getPassword());
        userService.saveAdmin(userForm);
        return "redirect:/admin/admins";
    }


}

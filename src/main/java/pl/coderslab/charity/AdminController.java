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

import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
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
            return "app/admin/adminadd";
        }
        userService.saveAdmin(userForm);
        return "redirect:/admin/admins";
    }


    @GetMapping("/admins/delete/{adminId}")
    public String deleteAdminForm(      @AuthenticationPrincipal CurrentUser customUser,
                                        Model model,
                                        @PathVariable("adminId") Long adminId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("adminToDelete", userRepository.getById(adminId));
        return "app/admin/admindelete";

    }

    @GetMapping("/admins/delete/{adminId}/confirm")
    public String doDeleteInstitution(@AuthenticationPrincipal CurrentUser customUser,
                                      @PathVariable("adminId") Long adminId) {

       userRepository.delete(userRepository.getById(adminId));

        return "redirect:/admin/admins/";


    }

}

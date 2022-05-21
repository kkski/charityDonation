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
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String showUsers(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("users", userService.findAllWhereRoleIsUser());
        return "app/users/users";


    }

    @GetMapping("/add")
    public String addUser(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("userForm", new UserDto());
        return "app/users/useradd";
    }

    @PostMapping("/add")
    public String doAddUser(@Valid @ModelAttribute("userForm") UserDto userForm,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "app/users/useradd";
        }
        userService.saveUser(userForm);
        return "redirect:/admin/users";
    }


    @GetMapping("/delete/{userId}")
    public String deleteUserForm(      @AuthenticationPrincipal CurrentUser customUser,
                                        Model model,
                                        @PathVariable("userId") Long userId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("userToDelete", userRepository.getById(userId));
        return "app/users/userdelete";

    }

    @GetMapping("/delete/{userId}/confirm")
    public String doDeleteInstitution(@AuthenticationPrincipal CurrentUser customUser,
                                      @PathVariable("userId") Long userId) {

       userRepository.delete(userRepository.getById(userId));

        return "redirect:/admin/users";


    }

    @GetMapping("/block/{userId}")
    public String blockUserForm(      @AuthenticationPrincipal CurrentUser customUser,
                                       Model model,
                                       @PathVariable("userId") Long userId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("userToBlock", userRepository.getById(userId));
        return "app/users/userblock";

    }

    @GetMapping("/block/{userId}/confirm")
    public String doBlockUser(@AuthenticationPrincipal CurrentUser customUser,
                                      @PathVariable("userId") Long userId) {

        User toBlock = userRepository.getById(userId);
        toBlock.setEnabled(0);
        userRepository.save(toBlock);

        return "redirect:/admin/users";


    }

    @GetMapping("/unblock/{userId}")
    public String unblockUserForm(      @AuthenticationPrincipal CurrentUser customUser,
                                      Model model,
                                      @PathVariable("userId") Long userId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("userToUnblock", userRepository.getById(userId));
        return "app/users/userunblock";

    }

    @GetMapping("/unblock/{userId}/confirm")
    public String doUnblockUser(@AuthenticationPrincipal CurrentUser customUser,
                              @PathVariable("userId") Long userId) {

        User toUnblock = userRepository.getById(userId);
        toUnblock.setEnabled(1);
        userRepository.save(toUnblock);

        return "redirect:/admin/users";


    }

}

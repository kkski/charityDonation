package pl.coderslab.charity;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserDto;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final InstitutionRepository institutionRepository;

    public AdminController(UserService userService, InstitutionRepository institutionRepository) {
        this.userService = userService;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/institutions")
    public String showInstitutions(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("institutions", institutionRepository.findAll(PageRequest.ofSize(10)).getContent());
        return "institutions";


    }

    @GetMapping("/institutions/add")
    public String addInstitutions(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("institution", new Institution());
        return "institutionsadd";
    }

    @PostMapping("/institutions/add")
    public String register(@Valid @ModelAttribute("institution") Institution institution,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/institutions/add";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institutions";
    }
}

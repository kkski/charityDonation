package pl.coderslab.charity;

import org.springframework.data.domain.PageRequest;
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
        return "app/institutions/institutions";


    }

    @GetMapping("/institutions/add")
    public String addInstitutions(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("institution", new Institution());
        return "app/institutions/institutionsadd";
    }

    @PostMapping("/institutions/add")
    public String doAddInstitutions(@Valid @ModelAttribute("institution") Institution institution,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/institutions/add";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institutions";
    }


    @GetMapping("/institutions/edit/{institutionId}")
    public String editInstitutionForm(@AuthenticationPrincipal CurrentUser customUser,
                                      Model model,
                                      @PathVariable("institutionId") Long institutionId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("institutionToChange", institutionRepository.getById(institutionId));
        model.addAttribute("institution", new Institution());
        return "app/institutions/institutionsedit";

    }

    @PostMapping("/institutions/edit/{institutionId}")
    public String editInspection(@Valid @ModelAttribute("institution") Institution institution,
                                 BindingResult bindingResult,
                                 @PathVariable("institutionId") Long institutionId
    ) {


        if (bindingResult.hasErrors()) {
            return "app/institutions/institutionsedit";
        }


        Institution myInstitution = institutionRepository.getById(institutionId);


        myInstitution.setDescription(institution.getDescription());
        myInstitution.setName(institution.getName());
        institutionRepository.save(myInstitution);
        return "redirect:/admin/institutions/";


    }


    @GetMapping("/institutions/delete/{institutionId}")
    public String deleteInstitutionForm(@AuthenticationPrincipal CurrentUser customUser,
                                      Model model,
                                      @PathVariable("institutionId") Long institutionId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUsername(entityUser.getUsername());
        model.addAttribute("user", myUser);
        model.addAttribute("institutionToDelete", institutionRepository.getById(institutionId));
        return "app/institutions/institutionsdelete";

    }

    @GetMapping("/institutions/delete/{institutionId}/confirm")
    public String doDeleteInstitution(@PathVariable("institutionId") Long institutionId
    ) {

        institutionRepository.delete(institutionRepository.getById(institutionId));

        return "redirect:/admin/institutions/";


    }

}

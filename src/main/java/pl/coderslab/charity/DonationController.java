package pl.coderslab.charity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Status;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;
import pl.coderslab.charity.services.CurrentUser;
import pl.coderslab.charity.services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app/donations")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class DonationController {
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final UserService userService;

    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository, UserService userService) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.userService = userService;
    }

    @ModelAttribute
    public void init(Model model,
                     @AuthenticationPrincipal CurrentUser customUser) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());

        User entityUser = customUser.getUser();

        User myUser = userService.findByUsername(entityUser.getUsername());

        model.addAttribute("user", myUser);

    }

    @GetMapping("/donation")
    public String donateAction(Model model,
                               @Valid @ModelAttribute("donation") Donation donation,
                               BindingResult bindingResult) {

        if (categoryRepository.findAll().size() == 0) {
            List<Category> emptyCategories = new ArrayList<>();
            model.addAttribute("donation", new Donation());
            model.addAttribute("categories", emptyCategories);
            return "app/donations/donationform";
        }

        model.addAttribute("donation", new Donation());

        return "app/donations/donationform";
    }

    @PostMapping("/donation")
    public String addDonation(
            @Valid @ModelAttribute("donation") Donation donation,
            BindingResult bindingResult,
            @AuthenticationPrincipal CurrentUser customUser
    ) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        User entityUser = customUser.getUser();
        Donation myDonation = new Donation();

        myDonation.setCategories(donation.getCategories());
        myDonation.setCity(donation.getCity());
        myDonation.setInstitution(donation.getInstitution());
        myDonation.setPickUpComment(donation.getPickUpComment());
        myDonation.setPickUpDate(donation.getPickUpDate());
        myDonation.setPickUpTime(donation.getPickUpTime());
        myDonation.setQuantity(donation.getQuantity());
        myDonation.setStreet(donation.getStreet());
        myDonation.setZipCode(donation.getZipCode());
        myDonation.setUser(userService.findByUsername(entityUser.getUsername()));
        Status status = new Status();
        status.setDonation(myDonation);
        myDonation.setStatus(status);
        donationRepository.save(myDonation);

        return "redirect:/app";

    }
}

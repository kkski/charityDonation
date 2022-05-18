package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.DonationRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class DonationController {
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;

    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
    }

    @ModelAttribute
    public void init(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());

    }

    @GetMapping("/donation")
    public String donateAction(Model model,
                               @Valid @ModelAttribute("donation") Donation donation,
                               BindingResult bindingResult) {

        if (categoryRepository.findAll().size() == 0) {
            List<Category> emptyCategories = new ArrayList<>();
            model.addAttribute("donation", new Donation());
            model.addAttribute("categories", emptyCategories);
            return "form";
        }

        model.addAttribute("donation", new Donation());

        return "form";
    }

    @PostMapping("/donation")
    public String addDonation(
            @Valid @ModelAttribute("donation") Donation donation,
                              BindingResult bindingResult
                             ) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

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

        donationRepository.save(myDonation);

        return "redirect:/";

    }
}

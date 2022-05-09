package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repositories.CategoryRepository;
import pl.coderslab.charity.repositories.InstitutionRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;

    public DonationController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("")
    public String donateAction(Model model,
                               @Valid @ModelAttribute("donation") Donation donation,
                               BindingResult bindingResult) {
        model.addAttribute("institutions", institutionRepository.findAll());
        if (categoryRepository.findAll().size() == 0) {
            List<Category> emptyCategories = new ArrayList<>();
            model.addAttribute("donation", new Donation());
            model.addAttribute("categories", emptyCategories);
            return "form";
        }
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        return "form";
    }
}

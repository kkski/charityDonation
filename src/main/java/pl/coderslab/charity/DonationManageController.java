package pl.coderslab.charity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Status;
import pl.coderslab.charity.services.DonationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/app/donations/{donationId}")
@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
public class DonationManageController {

    private final DonationService donationService;


    public DonationManageController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("")
    private String showDonation(@PathVariable("donationId") Long donationId,
                                Model model) {
        model.addAttribute("donation", donationService.findDonationById(donationId));
        model.addAttribute("status", donationService.findDonationById(donationId).getStatus());
        return "app/donations/donation";

    }

    @GetMapping("/approve")
    private String approveDonation(@PathVariable("donationId") Long donationId) {

             donationService.approveDonation(donationId);

        return "redirect:/app/donations";

    }

}

package pl.coderslab.charity.services;

import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repositories.DonationRepository;

import java.util.List;

public interface DonationService {

    List<Donation> findDonationsByUserId(Long userId);
    Donation findDonationById(Long userId);

    void approveDonation(Long donationId);

}

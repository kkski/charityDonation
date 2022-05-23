package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Status;
import pl.coderslab.charity.repositories.DonationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> findDonationsByUserId(Long userId) {
        return donationRepository.findAllByUserId(userId);
    }

    @Override
    public Donation findDonationById(Long userId) {
        return donationRepository.getById(userId);
    }

    @Override
    public void approveDonation(Long donationId) {
        Donation mydon = donationRepository.getById(donationId);
        Status mystat = mydon.getStatus();
        mystat.setClickMoment(LocalDateTime.now());
        mystat.setPickedUp(true);
        mydon.setStatus(mystat);
        donationRepository.save(mydon);
    }
}

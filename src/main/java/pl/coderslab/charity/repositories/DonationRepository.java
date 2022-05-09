package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    //Long countByName();
}

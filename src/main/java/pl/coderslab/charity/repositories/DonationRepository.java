package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Donation;

import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    //countAllBags
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Optional<Integer> findAllQuantityAndSum();
//    @Query("SELECT COUNT(d) FROM Donation d")
//    Integer countAll();
}

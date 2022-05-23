package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    //countAllBags
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Optional<Integer> findAllQuantityAndSum();


    @Query("select d from Donation d where d.user.id = ?1")
    List<Donation> findAllByUserId(Long userId);
}

package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.SecureToken;

public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {

    @Query("select s from SecureToken s where s.token = ?1")
    SecureToken findByToken(String token);
}

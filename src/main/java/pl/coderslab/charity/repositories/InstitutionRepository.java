package pl.coderslab.charity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query("select i from Institution i")
    List<Institution> findAll();

    @Query("select count(i) from Institution i")
    Integer countAll();

}

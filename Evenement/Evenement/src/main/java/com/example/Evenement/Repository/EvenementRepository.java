
package com.example.Evenement.Repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Evenement.Entities.Evenement;



@Repository
public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Optional<Evenement> findByNomEvenement(String nomEvenement);
}

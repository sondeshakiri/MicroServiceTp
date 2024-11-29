
package com.example.Evenement.Services;
import java.util.List;

import com.example.Evenement.Entities.Evenement;



public interface EvenementService {
    Evenement ajouterEvenement(Evenement evenement);
    List<Evenement> listerEvenements();
    Evenement getEvenement(Long id);
}

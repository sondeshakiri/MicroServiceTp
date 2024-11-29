package com.example.Internaute.Service;

import java.util.List;
import com.example.Internaute.Entities.Internaute;

public interface InternauteService {
    /**
     * Ajouter un internaute.
     * 
     * @param internaute L'objet Internaute à ajouter.
     * @return L'internaute ajouté.
     */
    Internaute ajouterInternaute(Internaute internaute);

    /**
     * Lister tous les internautes.
     * 
     * @return Liste des internautes.
     */
    List<Internaute> listerInternautes();

    /**
     * Récupérer un internaute par son ID.
     * 
     * @param id L'identifiant de l'internaute.
     * @return L'internaute correspondant.
     * @throws RuntimeException si l'internaute n'est pas trouvé.
     */
    Internaute getInternaute(Long id);
}

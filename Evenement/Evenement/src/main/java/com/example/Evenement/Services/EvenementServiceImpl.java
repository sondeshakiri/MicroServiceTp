
package com.example.Evenement.Services;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.Evenement.Entities.Categorie;
import com.example.Evenement.Entities.Evenement;
import com.example.Evenement.Repository.CategorieRepository;
import com.example.Evenement.Repository.EvenementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    // Méthode pour ajouter un nouvel événement avec des catégories
    @Override
    public Evenement ajouterEvenement(Evenement evenement) {
        // Vérifie si les catégories existent par leurs ID
        List<Categorie> categories = evenement.getCategories().stream()
                .map(categorie -> {
                    // Récupère la catégorie par son ID
                    Categorie existingCategorie = categorieRepository.findById(categorie.getCodeCategorie()).orElse(null);
                    if (existingCategorie == null) {
                        // Si la catégorie n'existe pas, lance une exception
                        throw new RuntimeException("Catégorie non trouvée : " + categorie.getCodeCategorie());
                    }
                    return existingCategorie;
                })
                .collect(Collectors.toList());

        // Associe les catégories à l'événement
        evenement.setCategories(categories);

        // Enregistre l'événement avec les catégories associées
        return evenementRepository.save(evenement);
    }

    // Méthode pour lister tous les événements
    @Override
    public List<Evenement> listerEvenements() {
        return evenementRepository.findAll();
    }

    // Méthode pour obtenir un événement par ID
    @Override
    public Evenement getEvenement(Long id) {
        return evenementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Événement non trouvé avec l'ID : " + id));
    }

    // Méthode planifiée pour afficher les événements par catégorie toutes les 15 secondes
    @Scheduled(fixedRate = 15000)  // 15000 ms = 15 secondes
    public void listeEvenementsParCategorie() {
        // Récupère tous les événements
        List<Evenement> evenements = evenementRepository.findAll();

        // Pour chaque événement, afficher ses catégories
        for (Evenement evenement : evenements) {
            System.out.println("Événements pour l'ID : " + evenement.getId() + " - " + evenement.getId());
            for (Categorie categorie : evenement.getCategories()) {
                System.out.println("- " + categorie.getNomCategorie());
            }
            System.out.println();
        }
    }
}

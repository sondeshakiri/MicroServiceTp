

package com.example.Evenement.Services;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Evenement.Entities.Categorie;
import com.example.Evenement.Repository.CategorieRepository;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    // Implement the method to add a new category
    @Override
    public Categorie ajouterCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Implement the method to list all categories
    @Override
    public List<Categorie> listerCategories() {
        return categorieRepository.findAll();
    }

    // Implement the method to get a category by its code
    @Override
    public Categorie getCategorie(String codeCategorie) {
        return categorieRepository.findById(codeCategorie).orElse(null);
    }
}

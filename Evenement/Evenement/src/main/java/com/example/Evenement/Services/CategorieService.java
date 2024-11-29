
package com.example.Evenement.Services;


import java.util.List;

import com.example.Evenement.Entities.Categorie;



public interface CategorieService {

    // Add a new category
    Categorie ajouterCategorie(Categorie categorie);

    // List all categories
    List<Categorie> listerCategories();

    // Get a category by its code
    Categorie getCategorie(String codeCategorie);
}

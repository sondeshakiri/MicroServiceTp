
package com.example.Evenement.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Evenement.Entities.Categorie;
import com.example.Evenement.Services.CategorieService;

import java.util.List;

@RestController
@RequestMapping("/api/cat")  // Mapping de base pour toutes les routes dans ce contrôleur
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    // Ajouter une nouvelle catégorie
    @PostMapping("/add")  // Cette route correspond à /api/cat/add
    public ResponseEntity<Categorie> ajouterCategorie(@RequestBody Categorie categorie) {
        Categorie nouvelleCategorie = categorieService.ajouterCategorie(categorie);
        return new ResponseEntity<>(nouvelleCategorie, HttpStatus.CREATED);  // 201 Created
    }

    // Lister toutes les catégories
    @GetMapping
    public ResponseEntity<List<Categorie>> listerCategories() {
        List<Categorie> categories = categorieService.listerCategories();
        return categories.isEmpty() 
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)  // 204 No Content
                : new ResponseEntity<>(categories, HttpStatus.OK);  // 200 OK
    }

    // Obtenir une catégorie par code
    @GetMapping("/{codeCategorie}")
    public ResponseEntity<Categorie> getCategorie(@PathVariable String codeCategorie) {
        Categorie categorie = categorieService.getCategorie(codeCategorie);
        return categorie != null 
                ? new ResponseEntity<>(categorie, HttpStatus.OK)  // 200 OK
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found
    }
}

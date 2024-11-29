
package com.example.Evenement.Controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Evenement.Entities.Evenement;
import com.example.Evenement.Services.EvenementService;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @PostMapping
    public Evenement ajouterEvenement(@RequestBody Evenement evenement) {
        return evenementService.ajouterEvenement(evenement);
    }

    @GetMapping
    public List<Evenement> listerEvenements() {
        return evenementService.listerEvenements();
    }

    @GetMapping("/{id}")
    public Evenement getEvenement(@PathVariable Long id) {
        return evenementService.getEvenement(id);
    }
}

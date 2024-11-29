package com.example.Ticket.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Ticket.Entities.Ticket;
import com.example.Ticket.Entities.TypeTicket;
import com.example.Ticket.Services.TicketServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/tickets")  // URL de base pour accéder aux ressources tickets
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private final TicketServiceImpl ticketService;

    @Autowired
    public TicketController(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    // Endpoint pour ajouter des tickets
    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterTickets(@RequestBody List<Ticket> tickets,
                                            @RequestParam Long idEvenement,
                                            @RequestParam Long idInternaute) {
        // Validation des données entrantes
        if (idEvenement == null || idInternaute == null || tickets == null || tickets.isEmpty()) {
            return ResponseEntity.badRequest().body("Les données fournies sont invalides.");
        }

        try {
            // Appel au service
            List<Ticket> ajoutés = ticketService.ajouterTickets(tickets, idEvenement, idInternaute);
            return ResponseEntity.ok(ajoutés);
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout des tickets : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }

    // Endpoint pour calculer le montant récupéré par événement et type de ticket
    @GetMapping("/montant/{idEvenement}/{typeTicket}")
    public ResponseEntity<?> montantRecupereParEvtEtTypeTicket(@PathVariable Long idEvenement,
                                                               @PathVariable TypeTicket typeTicket) {
        try {
            Double montant = ticketService.montantRecupereParEvtEtTypeTicket(idEvenement, typeTicket);
            return ResponseEntity.ok(montant);
        } catch (Exception e) {
            logger.error("Erreur lors du calcul du montant : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }

    // Endpoint pour récupérer l'internaute le plus actif
    @GetMapping("/internaute-le-plus-actif")
    public ResponseEntity<?> internauteLePlusActif() {
        try {
            Long internauteId = ticketService.internauteLePlusActif();
            return ResponseEntity.ok(internauteId);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de l'internaute le plus actif : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }
}

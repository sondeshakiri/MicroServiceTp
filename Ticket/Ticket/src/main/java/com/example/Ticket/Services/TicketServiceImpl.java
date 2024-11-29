package com.example.Ticket.Services;

import java.util.List;

import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import  com.example.Ticket.Entities.Ticket;
import  com.example.Ticket.Entities.TypeTicket;
import  com.example.Ticket.Repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final RestTemplate restTemplate;  // Injection de RestTemplate

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, RestTemplate restTemplate) {
        this.ticketRepository = ticketRepository;
        this.restTemplate = restTemplate;  // Initialisation de RestTemplate
    }

    @Override
    public List<Ticket> ajouterTickets(List<Ticket> tickets, Long idEvenement, Long idInternaute) {
        // Utilisation de RestTemplate pour obtenir les détails de l'événement sous forme de Map
        String evenementUrl = "http://evenement-service/api/evenements/" + idEvenement;
        Map<String, Object> evenementMap = restTemplate.getForObject(evenementUrl, Map.class);  // Appel HTTP pour récupérer l'événement sous forme de Map

        // Utilisation de RestTemplate pour obtenir les détails de l'internaute sous forme de Map
        String internauteUrl = "http://internaute-service/api/internautes/" + idInternaute;
        Map<String, Object> internauteMap = restTemplate.getForObject(internauteUrl, Map.class);  // Appel HTTP pour récupérer l'internaute sous forme de Map

        // Récupérer les données spécifiques depuis les Maps
        Integer nbPlacesRestantes = (Integer) evenementMap.get("nbPlacesRestantes");  // Exemple de récupération d'un champ depuis la Map
        // Vous pouvez récupérer d'autres informations de manière similaire, selon les propriétés de vos objets Evenement et Internaute
        String evenementNom = (String) evenementMap.get("nom");  // Exemple de récupération du nom de l'événement
        String internauteNom = (String) internauteMap.get("nom");  // Exemple de récupération du nom de l'internaute

        // Vérification du nombre de places restantes
        if (tickets.size() > nbPlacesRestantes) {
            throw new UnsupportedOperationException("Nombre de places demandées indisponible");
        }

        // Sauvegarde des tickets dans la base de données
        for (Ticket ticket : tickets) {
            // Vous pouvez associer les données spécifiques de l'événement et de l'internaute au ticket ici
            ticket.setIdEvenement(idEvenement);
            ticket.setIdInternaute(idInternaute);
            // Si vous souhaitez ajouter plus de détails de l'événement ou de l'internaute, vous pouvez le faire ici
            ticketRepository.save(ticket);
        }

        // Mise à jour du nombre de places restantes dans l'événement
        nbPlacesRestantes -= tickets.size();  // Mise à jour manuelle des places restantes
        evenementMap.put("nbPlacesRestantes", nbPlacesRestantes);  // Mise à jour de la Map

        // Mettre à jour l'événement avec les nouvelles places restantes via un appel HTTP
        String updateEvenementUrl = "http://evenement-service/api/evenements";
        restTemplate.put(updateEvenementUrl, evenementMap);  // Mise à jour via un appel HTTP PUT avec la Map

        return tickets;  // Retourner les tickets enregistrés
    }

    @Override
    public Double montantRecupereParEvtEtTypeTicket(Long idEvenement, TypeTicket typeTicket) {
        // Calculer le montant total récupéré pour un événement donné et un type de ticket spécifique
        List<Ticket> tickets = ticketRepository.findByIdEvenementAndTypeTicket(idEvenement, typeTicket);
        return tickets.stream().mapToDouble(Ticket::getPrixTicket).sum();  // Somme des prix des tickets
    }

    @Override
    public Long internauteLePlusActif() {
        // Trouver l'internaute le plus actif (celui qui a acheté le plus de tickets)
        return ticketRepository.findAll().stream()
                .collect(Collectors.groupingBy(Ticket::getIdInternaute, Collectors.counting()))  // Grouper par ID d'internaute
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))  // Trouver l'internaute avec le plus de tickets
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new RuntimeException("Aucun internaute trouvé"));
    }
}

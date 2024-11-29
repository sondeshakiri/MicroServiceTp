package com.example.Ticket.Services;

import java.util.List;


import com.example.Ticket.Entities.Ticket;
import com.example.Ticket.Entities.TypeTicket;

public interface TicketService {
    List<Ticket> ajouterTickets(List<Ticket> tickets, Long idEvenement, Long idInternaute);
    Double montantRecupereParEvtEtTypeTicket(Long idEvenement, TypeTicket typeTicket);
    Long internauteLePlusActif();
}

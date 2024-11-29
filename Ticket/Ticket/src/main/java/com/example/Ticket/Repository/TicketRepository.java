package com.example.Ticket.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.example.Ticket.Entities.Ticket;
import  com.example.Ticket.Entities.TypeTicket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByIdEvenementAndTypeTicket(Long idEvenement, TypeTicket typeTicket);
    List<Ticket> findByIdInternaute(Long idInternaute);
}

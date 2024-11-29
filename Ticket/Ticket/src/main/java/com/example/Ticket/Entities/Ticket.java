package com.example.Ticket.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
public class Ticket {
    @Id
    private String codeTicket;
    private double prixTicket;

    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;

    private Long idEvenement; // ID de l'événement (récupéré via Evenement Service)
    private Long idInternaute; // ID de l'internaute (récupéré via Internaute Service)

    // Getters et Setters
    public String getCodeTicket() {
        return codeTicket;
    }

    public void setCodeTicket(String codeTicket) {
        this.codeTicket = codeTicket;
    }

    public double getPrixTicket() {
        return prixTicket;
    }

    public void setPrixTicket(double prixTicket) {
        this.prixTicket = prixTicket;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public Long getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Long idEvenement) {
        this.idEvenement = idEvenement;
    }

    public Long getIdInternaute() {
        return idInternaute;
    }

    public void setIdInternaute(Long idInternaute) {
        this.idInternaute = idInternaute;
    }
}



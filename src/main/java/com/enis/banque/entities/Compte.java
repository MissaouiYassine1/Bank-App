package com.enis.banque.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Compte {
    @Id
    private String rib;

    private double solde;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}


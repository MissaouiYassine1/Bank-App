package com.enis.banque.entities;

import com.enis.banque.entities.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comptes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

    @Id
    @NotEmpty(message = "Le RIB est obligatoire")
    @Size(min = 5, max = 30, message = "Le RIB doit contenir entre 5 et 30 caractères")
    @Column(length = 30, unique = true)
    private String rib;

    @PositiveOrZero(message = "Le solde ne peut pas être négatif")
    @Column(nullable = false)
    private double solde;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
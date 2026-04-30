package com.enis.banque.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteDTO {

    @NotEmpty(message = "Le RIB est obligatoire")
    @Size(min = 5, max = 30, message = "Le RIB doit contenir entre 5 et 30 caractères")
    private String rib;

    @PositiveOrZero(message = "Le solde ne peut pas être négatif")
    private double solde;

    private Long clientId;
    private String clientNom;
    private String clientPrenom;
}
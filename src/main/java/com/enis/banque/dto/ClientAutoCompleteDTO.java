package com.enis.banque.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientAutoCompleteDTO {
    private Long id;
    private String label;
    private String value;
}
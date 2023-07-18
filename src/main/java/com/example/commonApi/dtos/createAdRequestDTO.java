package com.example.commonApi.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class createAdRequestDTO {
    private String description;
    private LocalDate dateDep;
    private LocalDate dateArr;
    private String heureDep;
    private String heureArr;
    private BigDecimal prixPlace;
    private String lieuDep;
    private int nbrPlace;
    private String lieuArr;
    private boolean animaux_de_companie;
    private boolean cigarette ;
    private boolean aller_retour;
    private String descriptionvoyage;

}

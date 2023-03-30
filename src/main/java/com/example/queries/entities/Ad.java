package com.example.queries.entities;

import com.example.commonApi.enums.AdStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name="Annonce")
public class Ad {
    @Id
    private String id;
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


    @Enumerated(EnumType.STRING)
    private AdStatus status;
}

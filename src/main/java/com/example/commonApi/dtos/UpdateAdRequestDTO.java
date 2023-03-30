package com.example.commonApi.dtos;

import com.example.commonApi.enums.AdStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Data
public class UpdateAdRequestDTO {
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

}

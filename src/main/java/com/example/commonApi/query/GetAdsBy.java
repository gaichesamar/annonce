package com.example.commonApi.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAdsBy {
    private String lieuDep;
    private String lieuArr;
    private int nbrPlace;
    private String dateDep;
}

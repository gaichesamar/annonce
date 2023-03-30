package com.example.commonApi.events;

import com.example.commonApi.enums.AdStatus;
import lombok.Getter;

public class AdReservedEvent extends baseEvent<String> {
    @Getter
    private int nbrPlace;
    @Getter private AdStatus adStatus;

    public AdReservedEvent(String id, int nbrPlace) {
        super(id);
        this.nbrPlace=nbrPlace;
        this.adStatus= AdStatus.RESERVED;
    }
}

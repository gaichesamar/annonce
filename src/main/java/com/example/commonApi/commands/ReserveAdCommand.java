package com.example.commonApi.commands;

import lombok.Getter;

public class ReserveAdCommand extends baseCommand<String> {
    @Getter
    private int nbrPlace;
    public ReserveAdCommand(String id,int nbrPlace) {
        super(id);
        this.nbrPlace = nbrPlace;

    }
}

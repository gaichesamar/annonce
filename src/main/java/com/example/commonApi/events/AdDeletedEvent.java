package com.example.commonApi.events;

import com.example.commonApi.enums.AdStatus;
import lombok.Getter;

public class AdDeletedEvent extends baseEvent<String> {
    @Getter private AdStatus status;

    public AdDeletedEvent(String id) {
        super(id);
        this.status= AdStatus.DELETED; }


}

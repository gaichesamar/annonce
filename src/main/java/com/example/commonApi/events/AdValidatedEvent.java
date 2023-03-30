package com.example.commonApi.events;

import com.example.commonApi.enums.AdStatus;
import lombok.Getter;

public class AdValidatedEvent extends baseEvent<String> {
    @Getter private AdStatus status;
    public AdValidatedEvent(String id, AdStatus status)
    {
        super(id);
        this.status=status;
    }
}

package com.example.commonApi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public abstract class baseEvent<T>{
    @Getter private  T id;
}

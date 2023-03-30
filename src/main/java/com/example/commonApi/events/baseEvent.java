package com.example.commonApi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
public abstract class baseEvent<T>{
    @Getter private  T id;
}

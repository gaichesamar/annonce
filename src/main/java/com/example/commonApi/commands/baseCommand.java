package com.example.commonApi.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@AllArgsConstructor
public abstract class baseCommand<T> {
    @TargetAggregateIdentifier
    @Getter
    private T id;
}

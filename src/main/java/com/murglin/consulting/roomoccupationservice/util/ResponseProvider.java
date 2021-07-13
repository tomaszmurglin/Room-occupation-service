package com.murglin.consulting.roomoccupationservice.util;

public interface ResponseProvider<T, Z> {

    T provide(Z dto);
}

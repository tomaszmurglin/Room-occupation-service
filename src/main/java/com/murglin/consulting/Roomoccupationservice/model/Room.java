package com.murglin.consulting.Roomoccupationservice.model;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class Room {

    private final UUID id;

    private final RoomCategory category;

    private final RoomStatus status;
}

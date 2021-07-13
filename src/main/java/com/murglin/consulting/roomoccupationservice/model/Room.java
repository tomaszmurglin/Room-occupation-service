package com.murglin.consulting.roomoccupationservice.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {

    private final UUID id;

    private final RoomCategory category;

    private final RoomStatus status;
}

package com.murglin.consulting.roomoccupationservice.model.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NumberOfRooms {

    @NotNull
    @Min(0)
    private int premium;

    @NotNull
    @Min(0)
    private int economy;
}

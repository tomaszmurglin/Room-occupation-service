package com.murglin.consulting.roomoccupationservice.model.dto.response;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NumberOfRoomsIncome {

    private int quantity;

    private BigDecimal income;
}

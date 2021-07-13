package com.murglin.consulting.roomoccupationservice.model;

import com.murglin.consulting.roomoccupationservice.model.dto.response.NumberOfRoomsIncome;
import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public final class BookingPlan {

    private NumberOfRoomsIncome premiumRoomsOccupied;

    private NumberOfRoomsIncome economyRoomsOccupied;

    private BigDecimal totalIncome;

    private String currencyCode;
}

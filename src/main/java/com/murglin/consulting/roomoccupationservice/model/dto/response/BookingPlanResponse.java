package com.murglin.consulting.roomoccupationservice.model.dto.response;

import com.murglin.consulting.roomoccupationservice.model.BookingPlan;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BookingPlanResponse {

    private NumberOfRoomsIncome premiumRoomsOccupied;

    private NumberOfRoomsIncome economyRoomsOccupied;

    private BigDecimal totalIncome;

    private String currencyCode;

    public BookingPlanResponse(final BookingPlan dto) {
        premiumRoomsOccupied = dto.getPremiumRoomsOccupied();
        economyRoomsOccupied = dto.getEconomyRoomsOccupied();
        totalIncome = dto.getTotalIncome();
        currencyCode = dto.getCurrencyCode();
    }
}

package com.murglin.consulting.roomoccupationservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public final class GuestPricing {

    private final BigDecimal pricing;

    private final LocalDate visitDate;

    public boolean isPremium(final BigDecimal premiumThreshold) {
        return pricing.compareTo(premiumThreshold) > 0 || pricing.compareTo(premiumThreshold) == 0;
    }
}

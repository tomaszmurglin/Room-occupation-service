package com.murglin.consulting.roomoccupationservice.repository;

import com.murglin.consulting.roomoccupationservice.model.GuestPricing;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GuestPricingRepository implements GuestPricingRepositoryI {

    private final List<GuestPricing> guestPricingInEuro = List.of(
            new GuestPricing(new BigDecimal("23"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("45"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("155"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("374"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("22"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("99.99"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("100"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("101"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("115"), LocalDate.of(2021, 5, 12)),
            new GuestPricing(new BigDecimal("209"), LocalDate.of(2021, 5, 12)));

    @Override
    public List<GuestPricing> findAllByGivenDate(final LocalDate date) {
        return guestPricingInEuro
                .stream()
                .filter(pricing -> pricing.getVisitDate().equals(date))
                .collect(Collectors.toUnmodifiableList());
    }
}

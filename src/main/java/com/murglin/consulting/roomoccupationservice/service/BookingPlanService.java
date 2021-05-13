package com.murglin.consulting.roomoccupationservice.service;

import com.murglin.consulting.roomoccupationservice.model.BookingPlan;
import com.murglin.consulting.roomoccupationservice.model.GuestPricing;
import com.murglin.consulting.roomoccupationservice.model.dto.request.BookingPlanRequest;
import com.murglin.consulting.roomoccupationservice.model.dto.response.NumberOfRoomsIncome;
import com.murglin.consulting.roomoccupationservice.repository.GuestPricingRepositoryI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingPlanService implements BookingPlanServiceI {

    private final GuestPricingRepositoryI guestPricingRepository;

    @Value("${roomoccupation.premiumpricing.greaterorequal.eur:100}")
    private String premiumPricing;

    //TODO probably ACID transcation needed
    @Override
    public BookingPlan plan(final BookingPlanRequest bookingPlanRequest) {
        //TODO publish metrics to monitoring e.g: NewRelic, DataDog, AWS CloudWatch
        log.info("Starting creating booking plan for '{}'", bookingPlanRequest);
        final var visitDate = bookingPlanRequest.getVisitDate();
        final var guestsPricings = guestPricingRepository.findAllByGivenDate(visitDate);
        final var premiumPricingThreshold = new BigDecimal(premiumPricing);
        final var requestedPremiumRoomsQuantity = bookingPlanRequest.getAvailableRoomsQuantity().getPremium();
        final var premiumPricingsDescending = guestsPricings
                .stream()
                .filter(pricing -> pricing.isPremium(premiumPricingThreshold))
                .map(GuestPricing::getPricing)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toUnmodifiableList());
        final var economyPricingsDescending = guestsPricings
                .stream()
                .filter(pricing -> !pricing.isPremium(premiumPricingThreshold))
                .map(GuestPricing::getPricing)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toUnmodifiableList());
        final var premiumRoomsLeftToAccumulatedPricing =
                calculatePremiumRoomsLeftAndAccumulatedPricing(requestedPremiumRoomsQuantity, premiumPricingsDescending, BigDecimal.ZERO);
        var premiumRoomsLeft = premiumRoomsLeftToAccumulatedPricing.getLeft();
        var premiumRoomsIncome = premiumRoomsLeftToAccumulatedPricing.getRight();
        if (premiumRoomsLeft > 0 && !economyPricingsDescending.isEmpty()) {
            final var economyPricingEligibleForPremium = economyPricingsDescending.subList(0, premiumRoomsLeft);
            final var premiumRoomsLeftAfterCorrectionToAccumulatedPricing = calculatePremiumRoomsLeftAndAccumulatedPricing(premiumRoomsLeft, economyPricingEligibleForPremium, premiumRoomsIncome);
            premiumRoomsIncome = premiumRoomsIncome.add(premiumRoomsLeftAfterCorrectionToAccumulatedPricing.getRight());
            premiumRoomsLeft = premiumRoomsLeftAfterCorrectionToAccumulatedPricing.getLeft();
        }
        final var requestedEconomyRoomsQuantity = bookingPlanRequest.getAvailableRoomsQuantity().getEconomy();
        final var economyRoomsLeft = economyPricingsDescending.subList(premiumRoomsLeft, requestedEconomyRoomsQuantity);
        final var economyRoomsIncome = economyRoomsLeft
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        final var totalIncome = premiumRoomsIncome.add(economyRoomsIncome);
        final var premiumRoomsPlanned = requestedPremiumRoomsQuantity - premiumRoomsLeft;
        final var bookingPlan = BookingPlan.builder()
                .currencyCode(Currency.getInstance("EUR").getCurrencyCode())
                .economyRoomsOccupied(new NumberOfRoomsIncome(economyRoomsLeft.size(), economyRoomsIncome))
                .premiumRoomsOccupied(new NumberOfRoomsIncome(premiumRoomsPlanned, premiumRoomsIncome))
                .totalIncome(totalIncome)
                .build();
        final var totalNumberOfRoomsPlanned = economyRoomsLeft.size() + premiumRoomsLeft;
        if (totalNumberOfRoomsPlanned > requestedPremiumRoomsQuantity + requestedEconomyRoomsQuantity) {
            throw new IllegalStateException("Total quantity of rooms from the plan cannot exceed number of guests");
        }
        //TODO publish metrics to monitoring e.g: NewRelic, DataDog, AWS CloudWatch with elapsed time
        log.info("Finished successfully planning bookings for '{}'. The plan is '{}'", bookingPlanRequest, bookingPlan);
        return bookingPlan;
    }

    /**
     * Allocates premium pricing - returns left premium pricing quantity with accumulated income
     */
    private Pair<Integer, BigDecimal> calculatePremiumRoomsLeftAndAccumulatedPricing(final int premiumRoomsSupply,
                                                                                     final List<BigDecimal> premiumPricingsDescending,
                                                                                     final BigDecimal initialIncome) {
        final var premiumRoomsDemand = premiumPricingsDescending.size();
        final var premiumPricing = premiumPricingsDescending
                .stream()
                .limit(premiumRoomsSupply)
                .reduce(initialIncome, BigDecimal::add);
        final int premiumRoomsLeft = Math.max(premiumRoomsSupply - premiumRoomsDemand, 0);
        return Pair.of(premiumRoomsLeft, premiumPricing);
    }
}

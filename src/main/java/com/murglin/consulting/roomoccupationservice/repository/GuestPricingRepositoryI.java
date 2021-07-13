package com.murglin.consulting.roomoccupationservice.repository;

import com.murglin.consulting.roomoccupationservice.model.GuestPricing;

import java.time.LocalDate;
import java.util.List;

public interface GuestPricingRepositoryI {
    List<GuestPricing> findAllByGivenDate(LocalDate date);
}

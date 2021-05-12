package com.murglin.consulting.Roomoccupationservice.controller;

import com.murglin.consulting.Roomoccupationservice.model.dto.BookingPlanRequest;
import com.murglin.consulting.Roomoccupationservice.model.dto.BookingPlanResponse;
import com.murglin.consulting.Roomoccupationservice.service.BookingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookingPlanController {

    private final BookingPlanService bookingPlanService;

    @PostMapping("/booking-plan")
    public ResponseEntity<BookingPlanResponse> createBookingPlan(@Valid @RequestBody final BookingPlanRequest bookingPlanRequest) {

    }
}

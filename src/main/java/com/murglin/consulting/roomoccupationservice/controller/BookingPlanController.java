package com.murglin.consulting.roomoccupationservice.controller;

import com.murglin.consulting.roomoccupationservice.model.dto.request.BookingPlanRequest;
import com.murglin.consulting.roomoccupationservice.model.dto.response.BookingPlanResponse;
import com.murglin.consulting.roomoccupationservice.service.BookingPlanServiceI;
import com.murglin.consulting.roomoccupationservice.util.BookingPlanResponseProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BookingPlanController {

    private final BookingPlanServiceI bookingPlanService;

    @GetMapping(value = "/booking-plan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingPlanResponse> createBookingPlan(@Valid @RequestBody final BookingPlanRequest bookingPlanRequest) {
        var bookingPlan = bookingPlanService.plan(bookingPlanRequest);
        return new BookingPlanResponseProvider().provide(bookingPlan);
    }
}

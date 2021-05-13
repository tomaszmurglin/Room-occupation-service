package com.murglin.consulting.roomoccupationservice.service;

import com.murglin.consulting.roomoccupationservice.model.BookingPlan;
import com.murglin.consulting.roomoccupationservice.model.dto.request.BookingPlanRequest;

public interface BookingPlanServiceI {
    //TODO probably ACID transcation needed
    BookingPlan plan(BookingPlanRequest bookingPlanRequest);
}

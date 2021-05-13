package com.murglin.consulting.roomoccupationservice.util;

import com.murglin.consulting.roomoccupationservice.model.BookingPlan;
import com.murglin.consulting.roomoccupationservice.model.dto.response.BookingPlanResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;

public class BookingPlanResponseProvider implements ResponseProvider<ResponseEntity<BookingPlanResponse>, BookingPlan>{
    @Override
    public ResponseEntity<BookingPlanResponse> provide(final BookingPlan dto) {
        return ResponseEntity.ok(new BookingPlanResponse(dto));
    }
}

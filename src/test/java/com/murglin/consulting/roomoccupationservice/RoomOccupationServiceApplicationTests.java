package com.murglin.consulting.roomoccupationservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murglin.consulting.roomoccupationservice.model.dto.request.BookingPlanRequest;
import com.murglin.consulting.roomoccupationservice.model.dto.request.NumberOfRooms;
import com.murglin.consulting.roomoccupationservice.model.dto.response.BookingPlanResponse;
import com.murglin.consulting.roomoccupationservice.model.dto.response.NumberOfRoomsIncome;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RoomOccupationServiceApplicationTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("provideTestData")
    public void givenAvailableRoomsNumbersShouldReturnBookingPlan(final BookingPlanRequest input,
                                                                  final BookingPlanResponse expected) throws Exception {
        //given
        //when
        var resultActions = mvc.perform(MockMvcRequestBuilders.get("/booking-plan")
                .content(objectMapper.writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON));
        //then
        var result = resultActions.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        assertThat(objectMapper.readValue(result.getResponse().getContentAsString(), BookingPlanResponse.class)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(new BookingPlanRequest(LocalDate.of(2021, 5, 12), new NumberOfRooms(3, 3)),
                        new BookingPlanResponse(new NumberOfRoomsIncome(3, new BigDecimal("738")), new NumberOfRoomsIncome(3, new BigDecimal("167.99")), new BigDecimal("905.99"), "EUR")),
                Arguments.of(new BookingPlanRequest(LocalDate.of(2021, 5, 12), new NumberOfRooms(7, 5)), new BookingPlanResponse(new NumberOfRoomsIncome(6, new BigDecimal("1054")),
                        new NumberOfRoomsIncome(4, new BigDecimal("189.99")), new BigDecimal("1243.99"), "EUR")),
                Arguments.of(new BookingPlanRequest(LocalDate.of(2021, 5, 12), new NumberOfRooms(2, 7)), new BookingPlanResponse(new NumberOfRoomsIncome(2, new BigDecimal("583")),
                        new NumberOfRoomsIncome(4, new BigDecimal("189.99")), new BigDecimal("772.99"), "EUR")),
                Arguments.of(new BookingPlanRequest(LocalDate.of(2021, 5, 12), new NumberOfRooms(7, 1)), new BookingPlanResponse(new NumberOfRoomsIncome(7, new BigDecimal("1153")),
                        new NumberOfRoomsIncome(1, new BigDecimal("45.99")), new BigDecimal("1198.99"), "EUR"))
        );
    }

}

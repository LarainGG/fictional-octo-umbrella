package com.technicaltask.application.controller;

import com.technicaltask.application.model.Reservation;
import com.technicaltask.application.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReservationsControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    ReservationRepository reservationRepository;

    public ReservationsControllerIntegrationTest(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    void testGetReservationsWhenEmpty() throws Exception {
        this.mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void testGetReservations() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setGuestName("test");
        reservation.setDateFrom(LocalDate.now());
        reservation.setDateTo(LocalDate.now());
        this.reservationRepository.save(reservation);

        this.mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id")
                    .isNotEmpty());
        this.reservationRepository.delete(reservation);
    }
}

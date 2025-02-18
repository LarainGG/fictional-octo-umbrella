package com.technicaltask.application.controller;

import com.technicaltask.application.model.Reservation;
import com.technicaltask.application.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ReservationControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    ReservationRepository reservationRepository;

    public ReservationControllerIntegrationTest(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    void testCreateReservation() throws Exception {
        this.mockMvc.perform(post("/api/reservation").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"guestName\": \"test\",\"dateFrom\":\"2025-01-01\",\"dateTo\":\"2025-01-02\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .isNotEmpty());
    }

    @Test
    void testDeleteReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setGuestName("test");
        reservation.setDateFrom(LocalDate.now());
        reservation.setDateTo(LocalDate.now());
        this.reservationRepository.save(reservation);

        this.mockMvc.perform(delete("/api/reservation/1"))
                .andExpect(status().isOk());

        this.reservationRepository.delete(reservation);
    }

    @Test
    void testDeleteNotFoundReservation() throws Exception {
        this.mockMvc.perform(delete("/api/reservation/1"))
                .andExpect(status().isNotFound());
    }

}

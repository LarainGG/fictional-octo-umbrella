package com.technicaltask.application.mapper;

import com.technicaltask.application.dto.ReservationDTO;
import com.technicaltask.application.model.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {

    @Test
    void testMapToDTO() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setGuestName("John Doe");
        reservation.setDateFrom(LocalDate.of(2025, 2, 18));
        reservation.setDateTo(LocalDate.of(2025, 2, 20));

        ReservationDTO reservationDTO = ReservationMapper.mapToDTO(reservation);

        assertNotNull(reservationDTO);
        assertEquals(reservation.getId(), reservationDTO.getId());
        assertEquals(reservation.getGuestName(), reservationDTO.getGuestName());
        assertEquals(reservation.getDateFrom(), reservationDTO.getDateFrom());
        assertEquals(reservation.getDateTo(), reservationDTO.getDateTo());
    }

    @Test
    void testMapFromDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1);
        reservationDTO.setGuestName("John Doe");
        reservationDTO.setDateFrom(LocalDate.of(2025, 2, 18));
        reservationDTO.setDateTo(LocalDate.of(2025, 2, 20));

        Reservation reservation = ReservationMapper.mapFromDTO(reservationDTO);

        assertNotNull(reservation);
        assertEquals(reservationDTO.getId(), reservation.getId());
        assertEquals(reservationDTO.getGuestName(), reservation.getGuestName());
        assertEquals(reservationDTO.getDateFrom(), reservation.getDateFrom());
        assertEquals(reservationDTO.getDateTo(), reservation.getDateTo());
    }
}
package com.technicaltask.application.mapper;

import com.technicaltask.application.dto.ReservationDTO;
import com.technicaltask.application.model.Reservation;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReservationMapper {
    public static ReservationDTO mapToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setGuestName(reservation.getGuestName());
        reservationDTO.setDateFrom(reservation.getDateFrom());
        reservationDTO.setDateTo(reservation.getDateTo());
        return reservationDTO;
    }

    public static Reservation mapFromDTO(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setGuestName(reservationDTO.getGuestName());
        reservation.setDateFrom(reservationDTO.getDateFrom());
        reservation.setDateTo(reservationDTO.getDateTo());
        return reservation;
    }
}

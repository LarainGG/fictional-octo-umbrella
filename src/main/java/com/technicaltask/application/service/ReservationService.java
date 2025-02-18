package com.technicaltask.application.service;

import com.technicaltask.application.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO create(ReservationDTO reservationDTO);
    ReservationDTO edit(int id, ReservationDTO reservationDTO);
    List<ReservationDTO> findAll();
    void delete(int id);
}

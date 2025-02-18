package com.technicaltask.application.service.impl;

import com.technicaltask.application.dto.ReservationDTO;
import com.technicaltask.application.exception.ReservationNotFoundException;
import com.technicaltask.application.mapper.ReservationMapper;
import com.technicaltask.application.model.Reservation;
import com.technicaltask.application.repository.ReservationRepository;
import com.technicaltask.application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationDTO create(ReservationDTO reservationDTO) {
        Reservation newReservation = ReservationMapper.mapFromDTO(reservationDTO);
        this.reservationRepository.save(newReservation);
        return ReservationMapper.mapToDTO(newReservation);
    }

    public List<ReservationDTO> findAll() {
        List<Reservation> reservations = this.reservationRepository.findAll();

        return reservations.stream().map(ReservationMapper::mapToDTO).toList();
    }

    public void delete(int id) {
        Reservation reservation = this.reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        reservation.setIsDeleted(true);
        this.reservationRepository.save(reservation);
    }

}

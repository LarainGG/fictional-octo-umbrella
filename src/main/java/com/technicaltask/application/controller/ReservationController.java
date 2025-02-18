package com.technicaltask.application.controller;

import com.technicaltask.application.dto.ReservationDTO;
import com.technicaltask.application.exception.ReservationNotFoundException;
import com.technicaltask.application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservation) {
        try {
            ReservationDTO newReservation = this.reservationService.create(reservation);
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> editReservation(@PathVariable("id") int id, @RequestBody ReservationDTO reservation) {
        try {
            ReservationDTO newReservation = this.reservationService.edit(id, reservation);
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        } catch (ReservationNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }  catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") int id) {
        try {
            this.reservationService.delete(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (ReservationNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
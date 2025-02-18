package com.technicaltask.application.controller;

import com.technicaltask.application.dto.ReservationDTO;
import com.technicaltask.application.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Reservations api", description="Api for viewing reservations")
@RestController
@RequestMapping("/api/reservations")
public class ReservationsController {
    @Autowired
    private ReservationService reservationService;

    @Operation(
            summary = "Retrieve reservations",
            description = "Get list of reservation objects with id, guest name, date from and date to",
            tags = { "reservations", "get" }
    )
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        return new ResponseEntity<>(this.reservationService.findAll(), HttpStatus.OK);
    }
}
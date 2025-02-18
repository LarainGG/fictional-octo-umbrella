package com.technicaltask.application.controller;

import com.technicaltask.application.dto.ReservationDTO;
import com.technicaltask.application.exception.ReservationNotFoundException;
import com.technicaltask.application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Reservation api", description="Api for manipulating reservations")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Operation(
            summary = "Create new reservation",
            description = "Create new reservation",
            tags = { "reservation", "post" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ReservationDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservation) {
        try {
            ReservationDTO newReservation = this.reservationService.create(reservation);
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Edit reservation",
            description = "Edit reservation",
            tags = { "reservation", "put" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ReservationDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Reservation with given id not found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) })
    })
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

    @Operation(
            summary = "Delete reservation",
            description = "Delete reservation",
            tags = { "reservation", "delete" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =  { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Reservation with given id not found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = { @Content(schema = @Schema()) })
    })
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
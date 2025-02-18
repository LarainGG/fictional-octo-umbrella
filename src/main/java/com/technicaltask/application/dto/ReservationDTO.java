package com.technicaltask.application.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private int id;
    private String guestName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}

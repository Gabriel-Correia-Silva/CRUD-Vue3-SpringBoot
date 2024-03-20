package com.npi.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventRecordDtos( String name,  LocalDate start, LocalDate endDate, String details, String color) {
}
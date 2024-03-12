package com.npi.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;
import java.util.Date;

public record EventRecordDtos(@NotBlank String name, @NotNull Date date, String description, @NotNull Time startTime, @NotNull Time endTime) {
}

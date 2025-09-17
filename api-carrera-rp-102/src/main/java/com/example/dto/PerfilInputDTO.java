package com.example.dto;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PerfilInputDTO(
        @NotBlank(message = "El nombre completo es obligatorio")
        String nombreCompleto,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El carnet es obligatorio")
        String carnet,

        @NotBlank(message = "La carrera es obligatoria")
        String carrera,

        @Min(value = 2000, message = "El cohorte debe ser mayor o igual a 2000")
        int cohorte
) {}

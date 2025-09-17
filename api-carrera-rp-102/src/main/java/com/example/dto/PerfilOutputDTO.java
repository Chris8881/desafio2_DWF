package com.example.dto;

import org.springframework.web.bind.annotation.PostMapping;

@PostMapping
public record PerfilOutputDTO(
        Long id,
        String nombreCompleto,
        String apellido,
        String carnet,
        String carrera,
        int cohorte
) {}

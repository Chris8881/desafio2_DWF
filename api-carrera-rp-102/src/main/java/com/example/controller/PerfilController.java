package com.example.controller;

import com.example.dto.PerfilInputDTO;
import com.example.dto.PerfilOutputDTO;
import com.example.service.PerfilService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public PerfilOutputDTO crear(@Valid @RequestBody PerfilInputDTO dto) {
        return perfilService.crear(dto);
    }

    @GetMapping
    public List<PerfilOutputDTO> listar() {
        return perfilService.listar();
    }

    @GetMapping("/{id}")
    public PerfilOutputDTO buscarPorId(@PathVariable Long id) {
        return perfilService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PerfilOutputDTO actualizar(@PathVariable Long id, @Valid @RequestBody PerfilInputDTO dto) {
        return perfilService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        perfilService.eliminar(id);
    }

    // Endpoint saludo
    @GetMapping("/saludo/{apellido}")
    public  String saludo(@PathVariable String apellido) {
        PerfilOutputDTO perfil = perfilService.listar().stream()
                .filter(p -> p.apellido().equalsIgnoreCase(apellido))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró el perfil con apellido: " + apellido));

        return "Hola " + perfil.nombreCompleto() + "! Estás estudiando la carrera de " + perfil.carrera();
    }
}

package com.example.service;

import com.example.dto.PerfilInputDTO;
import com.example.dto.PerfilOutputDTO;
import com.example.entity.Perfil;
import com.example.exception.NoEncontradoException;
import com.example.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilOutputDTO crear(PerfilInputDTO dto) {
        Perfil perfil = Perfil.builder()
                .nombreCompleto(dto.nombreCompleto())
                .apellido(dto.apellido())
                .carnet(dto.carnet())
                .carrera(dto.carrera())
                .cohorte(dto.cohorte())
                .build();
        return toDTO(perfilRepository.save(perfil));
    }

    public List<PerfilOutputDTO> listar() {
        return perfilRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public PerfilOutputDTO buscarPorId(Long id) {
        return perfilRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new NoEncontradoException("Perfil no encontrado con id: " + id));
    }

    public PerfilOutputDTO actualizar(Long id, PerfilInputDTO dto) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Perfil no encontrado con id: " + id));

        perfil.setNombreCompleto(dto.nombreCompleto());
        perfil.setApellido(dto.apellido());
        perfil.setCarnet(dto.carnet());
        perfil.setCarrera(dto.carrera());
        perfil.setCohorte(dto.cohorte());

        return toDTO(perfilRepository.save(perfil));
    }

    public void eliminar(Long id) {
        if (perfilRepository.existsById(id)) {
            throw new NoEncontradoException("Perfil no encontrado con id: " + id);
        }
        perfilRepository.deleteById(id);
    }

    private PerfilOutputDTO toDTO(Perfil perfil) {
        return new PerfilOutputDTO(
                perfil.getId(),
                perfil.getNombreCompleto(),
                perfil.getApellido(),
                perfil.getCarnet(),
                perfil.getCarrera(),
                perfil.getCohorte()
        );
    }
}


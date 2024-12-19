package com.carlos.converter;

import org.springframework.stereotype.Component;

import com.carlos.dto.EmpleadoDto;
import com.carlos.entity.Empleado;

@Component
public class EmpleadoConverter extends AbstractConverter<Empleado, EmpleadoDto> {

    @Override
    public EmpleadoDto fromEntity(Empleado entity) {
        if (entity == null) return null;
        return EmpleadoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .direccion(entity.getDireccion())
                .telefono(entity.getTelefono())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public Empleado fromDTO(EmpleadoDto dto) {
        if (dto == null) return null;
        return Empleado.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }
}

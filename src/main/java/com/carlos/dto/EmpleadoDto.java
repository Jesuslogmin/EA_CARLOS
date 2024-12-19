package com.carlos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDto {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}
package com.carlos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.carlos.entity.Empleado;

public interface EmpleadoService {

    List<Empleado> findAll(Pageable page);

    List<Empleado> findAll();

    List<Empleado> findByNombre(String nombre, Pageable page);

    List<Empleado> findByDireccion(String direccion, Pageable page);

    List<Empleado> findByTelefono(String telefono, Pageable page);

    List<Empleado> findByEmail(String email, Pageable page);

    Empleado findById(int id);

    Empleado save(Empleado empleado);

    void delete(int id);
}

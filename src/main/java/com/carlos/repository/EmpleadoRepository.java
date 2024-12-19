package com.carlos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlos.entity.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    List<Empleado> findByNombreContaining(String nombre, org.springframework.data.domain.Pageable page);
    List<Empleado> findByDireccionContaining(String direccion, org.springframework.data.domain.Pageable page);
    List<Empleado> findByTelefonoContaining(String telefono, org.springframework.data.domain.Pageable page);
    List<Empleado> findByEmailContaining(String email, org.springframework.data.domain.Pageable page);
}

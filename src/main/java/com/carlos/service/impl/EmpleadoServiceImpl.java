package com.carlos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carlos.entity.Empleado;
import com.carlos.repository.EmpleadoRepository;
import com.carlos.service.EmpleadoService;
import com.carlos.validator.EmpleadoValidator;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAll(Pageable page) {
        return empleadoRepository.findAll(page).getContent();
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public List<Empleado> findByNombre(String nombre, Pageable page) {
        return empleadoRepository.findByNombreContaining(nombre, page);
    }

    @Override
    public List<Empleado> findByDireccion(String direccion, Pageable page) {
        return empleadoRepository.findByDireccionContaining(direccion, page);
    }

    @Override
    public List<Empleado> findByTelefono(String telefono, Pageable page) {
        return empleadoRepository.findByTelefonoContaining(telefono, page);
    }

    @Override
    public List<Empleado> findByEmail(String email, Pageable page) {
        return empleadoRepository.findByEmailContaining(email, page);
    }

    @Override
    public Empleado findById(int id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado save(Empleado empleado) {

            EmpleadoValidator.validate(empleado);  
       
        return empleadoRepository.save(empleado);
    }

    @Override
    public void delete(int id) {
        empleadoRepository.deleteById(id);
    }
}

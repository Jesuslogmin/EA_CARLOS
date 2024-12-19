package com.carlos.validator;

import com.carlos.entity.Empleado;
import com.carlos.exception.ValidateException;

public class EmpleadoValidator {
    public static void validate(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre del empleado es requerido");
        }
        if (empleado.getNombre().length() > 100) {
            throw new ValidateException("El nombre del empleado no debe tener más de 100 caracteres");
        }

        if (empleado.getDireccion() == null || empleado.getDireccion().trim().isEmpty()) {
            throw new ValidateException("La dirección del empleado es requerida");
        }
        if (empleado.getDireccion().length() > 15) {
            throw new ValidateException("La dirección del empleado no debe tener más de 15 caracteres");
        }

        if (empleado.getTelefono() == null || empleado.getTelefono().trim().isEmpty()) {
            throw new ValidateException("El teléfono del empleado es requerido");
        }
        if (empleado.getTelefono().length() > 15) {
            throw new ValidateException("El teléfono del empleado no debe tener más de 15 caracteres");
        }

        if (empleado.getEmail() == null || empleado.getEmail().trim().isEmpty()) {
            throw new ValidateException("El correo electrónico del empleado es requerido");
        }
        if (empleado.getEmail().length() > 100) {
            throw new ValidateException("El correo electrónico del empleado no debe tener más de 100 caracteres");
        }
    }
}

package com.carlos.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import com.carlos.converter.EmpleadoConverter;
import com.carlos.dto.EmpleadoDto;
import com.carlos.entity.Empleado;
import com.carlos.service.EmpleadoService;
import com.carlos.service.PdfService;
import com.carlos.util.WrapperResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @Autowired
    private EmpleadoConverter converter;

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<EmpleadoDto> empleados = converter.fromEntity(service.findAll(page));

        return new WrapperResponse(true, "success", empleados).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmpleadoDto> create(@RequestBody EmpleadoDto empleado) {
        Empleado empleadoEntity = converter.fromDTO(empleado);
        EmpleadoDto registro = converter.fromEntity(service.save(empleadoEntity));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> update(@PathVariable("id") int id, @RequestBody EmpleadoDto empleado) {
        Empleado empleadoEntity = converter.fromDTO(empleado);
        empleadoEntity.setId(id); // Aseguramos que se actualiza el registro correspondiente
        EmpleadoDto registro = converter.fromEntity(service.save(empleadoEntity));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        service.delete(id);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> findById(@PathVariable("id") int id) {
        EmpleadoDto registro = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport() {
        List<EmpleadoDto> empleados = converter.fromEntity(service.findAll());

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = fecha.format(formato);

        Context context = new Context();
        context.setVariable("registros", empleados);
        context.setVariable("fecha", fechaHora);

        byte[] pdfBytes = pdfService.createPdf("empleadoReport", context);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "reporte_empleados.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}

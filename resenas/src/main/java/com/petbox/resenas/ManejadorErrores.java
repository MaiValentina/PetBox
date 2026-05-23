package com.petbox.resenas;

import com.petbox.resenas.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> miManejador(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> mapaErrores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensajeValidacion = error.getDefaultMessage();
            mapaErrores.put(campo, mensajeValidacion);
        });

        ErrorDTO errorDto = new ErrorDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación en la reseña.",
            mapaErrores,
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDTO> miManejadorBD(DataIntegrityViolationException ex, HttpServletRequest request) {
        Map<String, String> mapaErrores = new HashMap<>();
        mapaErrores.put("db", "Error de restricción o datos duplicados en la tabla de valoraciones.");

        ErrorDTO errorDto = new ErrorDTO(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            "Error de consistencia en base de datos de reseñas.",
            mapaErrores,
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> miManejadorLogica(IllegalArgumentException ex, HttpServletRequest request) {
        Map<String, String> mapaErrores = new HashMap<>();
        mapaErrores.put("error", ex.getMessage());

        ErrorDTO errorDto = new ErrorDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            "La reseña solicitada no existe.",
            mapaErrores,
            request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }
}

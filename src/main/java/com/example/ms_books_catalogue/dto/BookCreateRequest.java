package com.example.ms_books_catalogue.dto;

import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {
    private String titulo;
    private String autor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechapublicacion;
    private String categoria;
    private String isbn;
    private Double precio;
    private Integer calificacion;
    private Boolean visible;
    private Integer stock;
}

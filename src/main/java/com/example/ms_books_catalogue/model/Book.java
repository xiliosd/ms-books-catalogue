package com.example.ms_books_catalogue.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechapublicacion;
    private String categoria;
    private String isbn;
    private Integer calificacion;
    private Boolean visible;
    private Integer stock;
    private Double precio;
}

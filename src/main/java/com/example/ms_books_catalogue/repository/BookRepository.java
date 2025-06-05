package com.example.ms_books_catalogue.repository;

import com.example.ms_books_catalogue.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    List<Book> findByTituloContainingIgnoreCase(String titulo);
    List<Book> findByAutorContainingIgnoreCase(String autor);
    List<Book> findByCategoria(String categoria);
    List<Book> findByIsbn(String isbn);
    List<Book> findByCalificacion(Integer calificacion);
    List<Book> findByVisible(Boolean visible);

    List<Book> findByPrecio(Double precio);

    List<Book> findAll(Specification<Book> spec);
}

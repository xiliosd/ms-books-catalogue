package com.example.ms_books_catalogue.specs;

import com.example.ms_books_catalogue.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> hasTitulo(String titulo) {
        return (root, query, cb) -> titulo == null ? null :
                cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
    }

    public static Specification<Book> hasAutor(String autor) {
        return (root, query, cb) -> autor == null ? null :
                cb.like(cb.lower(root.get("autor")), "%" + autor.toLowerCase() + "%");
    }

    public static Specification<Book> hasCategoria(String categoria) {
        return (root, query, cb) -> categoria == null ? null :
                cb.equal(cb.lower(root.get("categoria")), categoria.toLowerCase());
    }

    public static Specification<Book> hasIsbn(String isbn) {
        return (root, query, cb) -> isbn == null ? null :
                cb.equal(cb.lower(root.get("isbn")), isbn.toLowerCase());
    }

    public static Specification<Book> hasCalificacion(Integer calificacion) {
        return (root, query, cb) -> calificacion == null ? null :
                cb.equal(root.get("calificacion"), calificacion);
    }

    public static Specification<Book> isVisible(Boolean visible) {
        return (root, query, cb) -> visible == null ? null :
                cb.equal(root.get("visible"), visible);
    }

    public static Specification<Book> isPrecio(Double precio) {
        return (root, query, cb) -> precio == null ? null :
                cb.equal(root.get("precio"), precio);
    }

}


package com.example.ms_books_catalogue.service;

import com.example.ms_books_catalogue.model.Book;
import com.example.ms_books_catalogue.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    // Obtener todos los libros
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    // Obtener un libro por ID
    public Book getBook(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Guardar un nuevo libro
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    // Eliminar un libro por ID
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    // Actualizar un libro completo
    public Book updateBook(Long id, Book updatedBook) {
        if (!repository.existsById(id)) return null;
        updatedBook.setId(id);
        return repository.save(updatedBook);
    }

    // Actualización parcial
    public Book patchBook(Long id, Map<String, Object> updates) {
        Book book = repository.findById(id).orElse(null);
        if (book == null) return null;

        updates.forEach((key, value) -> {
            try {
                if (key.equals("id")) return;
                Field field = Book.class.getDeclaredField(key);
                field.setAccessible(true);
                if (field.getType().equals(LocalDate.class) && value instanceof String) {
                    value = LocalDate.parse((String) value);
                }

                if (field.getType().equals(Integer.class) && value instanceof Number) {
                    value = ((Number) value).intValue();
                }

                if (field.getType().equals(Double.class) && value instanceof Number) {
                    value = ((Number) value).doubleValue();
                }

                if (field.getType().equals(Boolean.class) && value instanceof Boolean) {
                    value = value;
                }

                field.set(book, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Error actualizando el campo: " + key, e);
            }
        });

        return repository.save(book);
    }

    // Búsqueda combinada con Specification
    public List<Book> searchCombined(String titulo, String autor, String categoria, String isbn,
                                     Integer calificacion, Boolean visible, Double precio) {

        Specification<Book> spec = emptySpec();

        if (titulo != null && !titulo.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%"));
        }
        if (autor != null && !autor.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("autor")), "%" + autor.toLowerCase() + "%"));
        }
        if (categoria != null && !categoria.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("categoria"), categoria));
        }
        if (isbn != null && !isbn.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("isbn"), isbn));
        }
        if (calificacion != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("calificacion"), calificacion));
        }
        if (visible != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("visible"), visible));
        }
        if (precio != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("precio"), precio));
        }

        return repository.findAll(spec);
    }

    private Specification<Book> emptySpec() {

            return (root, query, cb) -> null;
      
    }
}

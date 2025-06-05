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

    // Búsquedas por campos individuales
    public List<Book> searchByTitulo(String titulo) {
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Book> searchByAutor(String autor) {
        return repository.findByAutorContainingIgnoreCase(autor);
    }

    public List<Book> searchByCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public List<Book> searchByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public List<Book> searchByCalificacion(Integer calificacion) {
        return repository.findByCalificacion(calificacion);
    }

    public List<Book> searchByVisible(Boolean visible) {
        return repository.findByVisible(visible);
    }

    public List<Book> searchByPrecio(Double precio) {
        return repository.findByPrecio(precio);
    }

    // Búsqueda combinada con Specification
    public List<Book> searchCombined(String titulo, String autor, String categoria, String isbn,
                                     Integer calificacion, Boolean visible, Double precio) {

        Specification<Book> spec = Specification.where(null);

        if (titulo != null && !titulo.isEmpty()) spec = spec.and(hasTitulo(titulo));
        if (autor != null && !autor.isEmpty()) spec = spec.and(hasAutor(autor));
        if (categoria != null && !categoria.isEmpty()) spec = spec.and(hasCategoria(categoria));
        if (isbn != null && !isbn.isEmpty()) spec = spec.and(hasIsbn(isbn));
        if (calificacion != null) spec = spec.and(hasCalificacion(calificacion));
        if (visible != null) spec = spec.and(isVisible(visible));
        if (precio != null) spec = spec.and(isPrecio(precio));

        return repository.findAll(spec);
    }

    // Métodos Specification para cada campo
    private Specification<Book> hasTitulo(String titulo) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
    }

    private Specification<Book> hasAutor(String autor) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("autor")), "%" + autor.toLowerCase() + "%");
    }

    private Specification<Book> hasCategoria(String categoria) {
        return (root, query, cb) -> cb.equal(root.get("categoria"), categoria);
    }

    private Specification<Book> hasIsbn(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    private Specification<Book> hasCalificacion(Integer calificacion) {
        return (root, query, cb) -> cb.equal(root.get("calificacion"), calificacion);
    }

    private Specification<Book> isVisible(Boolean visible) {
        return (root, query, cb) -> cb.equal(root.get("visible"), visible);
    }

    private Specification<Book> isPrecio(Double precio) {
        return (root, query, cb) -> cb.equal(root.get("precio"), precio);
    }
}

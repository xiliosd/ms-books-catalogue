package com.example.ms_books_catalogue.controller;

import com.example.ms_books_catalogue.model.Book;
import com.example.ms_books_catalogue.service.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books Controller", description = "Microservicio encargado de exponer operaciones de CRUD para libros")
public class BookController {

    @Autowired
    private BookService service;

    // Obtener todos los libros y búsqueda
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
            @Parameter(name = "titulo", description = "Título del libro")
            @RequestParam(required = false) String titulo,
            @Parameter(name = "autor", description = "Autor del libro")
            @RequestParam(required = false) String autor,
            @Parameter(name = "categoria", description = "Categoría del libro")
            @RequestParam(required = false) String categoria,
            @Parameter(name = "isbn", description = "Código ISBN del libro")
            @RequestParam(required = false) String isbn,
            @Parameter(name = "calificacion", description = "Calificación del libro de 1 a 5")
            @RequestParam(required = false) Integer calificacion,
            @Parameter(name = "visible", description = "¿El libro es visible?")
            @RequestParam(required = false) Boolean visible,
            @Parameter(name = "precio", description = "Precio del libro, debe ser exacto")
            @RequestParam(required = false) Double precio
    ) {
        List<Book> books;
        if (titulo == null && autor == null && categoria == null && isbn == null &&
                calificacion == null && visible == null && precio == null) {
            books = service.getAllBooks();
        } else {
            books = service.searchCombined(titulo, autor, categoria, isbn, calificacion, visible, precio);
        }
        return ResponseEntity.ok(books);
    }

    // Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = service.getBook(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nuevo libro
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = service.saveBook(book);
        return ResponseEntity.status(201).body(createdBook);
    }

    // Actualizar libro completo
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = service.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar parcialmente un libro
    @PatchMapping("/{id}")
    public ResponseEntity<Book> patchBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Book patchedBook = service.patchBook(id, updates);
        if (patchedBook != null) {
            return ResponseEntity.ok(patchedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = service.getBook(id);
        if (book != null) {
            service.deleteBook(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


package com.example.ms_books_catalogue.controller;

import com.example.ms_books_catalogue.model.Book;
import com.example.ms_books_catalogue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    // Busqueda de todos los libros
    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }
    //Patch
    @PatchMapping("/{id}")
    public Book patchBook(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return service.patchBook(id, updates);
    }

    // busqueda de libro por ID
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    //Busqueda combinada
    @GetMapping("/search")
    public List<Book> searchCombined(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) Integer calificacion,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Double precio
    ) {
        return service.searchCombined(titulo, autor, categoria, isbn, calificacion, visible, precio);
    }


    // crear nuevo libro
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    // actualizar libro completo
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    // eliminar libro
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
    }

    // Rutas de búsqueda individuales
    @GetMapping("/search/titulo")
    public List<Book> searchByTitulo(@RequestParam String titulo) {
        return service.searchByTitulo(titulo);
    }

    @GetMapping("/search/autor")
    public List<Book> searchByAutor(@RequestParam String autor) {
        return service.searchByAutor(autor);
    }

    @GetMapping("/search/categoria")
    public List<Book> searchByCategoria(@RequestParam String categoria) {
        return service.searchByCategoria(categoria);
    }

    @GetMapping("/search/isbn")
    public List<Book> searchByIsbn(@RequestParam String isbn) {
        return service.searchByIsbn(isbn);
    }

    @GetMapping("/search/calificacion")
    public List<Book> searchByCalificacion(@RequestParam Integer calificacion) {
        return service.searchByCalificacion(calificacion);
    }

    @GetMapping("/search/visible")
    public List<Book> searchByVisible(@RequestParam Boolean visible) {
        return service.searchByVisible(visible);
    }

    @GetMapping("/search/precio")
    public List<Book> searchByPrecio(@RequestParam Double precio) {
        return service.searchByPrecio(precio);
    }
}

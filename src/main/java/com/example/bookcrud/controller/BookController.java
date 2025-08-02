package com.example.bookcrud.controller;

import com.example.bookcrud.model.Book;
import com.example.bookcrud.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    // Create a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return repo.save(book);
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = repo.findById(id);
        return book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a book by ID
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> optionalBook = repo.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setGenre(updatedBook.getGenre());
            book.setAuthor(updatedBook.getAuthor());
            book.setYear(updatedBook.getYear());
            book.setPrice(updatedBook.getPrice());
            return repo.save(book);
        } else {
            return null;
        }
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

package com.sprints.PersonalLibraryService.controller;

import com.sprints.PersonalLibraryService.model.Book;
import com.sprints.PersonalLibraryService.repository.BookRepository;
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

    //we can find a book by author using the ORM built query:
    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return repo.findByAuthor(author);
    }


    // way 1
    @GetMapping("/author/{author}/genre/{genre}")
    public List<Book> getBooksByAuthorAndGenre(@PathVariable String author, @PathVariable String genre){
        return repo.findByAuthorAndGenre(author, genre);
    }

    //way 2:
    // example usage:
    // /books/search?author=Agatha Christie&genre=Mystery
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByAuthorAndGenre2(@RequestParam String author, @RequestParam String genre) {
        List<Book> books = repo.findByAuthorAndGenre(author, genre);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();   // 404 not found
    //      return ResponseEntity.noContent().build();  // HTTP 204 No Content
        } else {
            return ResponseEntity.ok(books);
        }
    }

    // using ResponseEntity vs not using it:
    //so basically not using it we will always return 200 ok even if we didnt find anything or whatever

    /*  simple way:

       @GetMapping("/books")
        public List<Book> getAllBooks() {
            return repo.findAll();
        }

    returns:
        HTTP/1.1 200 OK
        Content-Type: application/json

        []

    using reponseEntity as above, we can control the HTTP status codes and will return sth like:


        HTTP/1.1 200 OK
        Content-Type: application/json

        [
            {
                "id": 1,
                "title": "Book Title",
                "author": "Author Name"
            }
        ]

        OR:

        HTTP/1.1 404 Not Found

        or:

        HTTP/1.1 204 No Content


    * */


}

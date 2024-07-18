package com.example.liberarymanagementsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.liberarymanagementsystem.Entity.Book;
import com.example.liberarymanagementsystem.Services.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            Book savedBook = libraryService.addBook(book);
            return ResponseEntity.ok(savedBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(null); 
        }
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<Void> removeBook(@PathVariable String isbn) {
        try {
            LibraryService.removeBook(isbn);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/books/title/{title}")
    public ResponseEntity<List<Book>> findBooksByTitle(@PathVariable String title) {
        List<Book> books = libraryService.findBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> findBooksByAuthor(@PathVariable String author) {
        List<Book> books = libraryService.findBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = libraryService.listAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/available")
    public ResponseEntity<List<Book>> listAvailableBooks() {
        List<Book> books = libraryService.listAvailableBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/books/department/{department}")
    public ResponseEntity<List<Book>> listBooksByDepartment(@PathVariable String department) {
        List<Book> books = libraryService.listBooksByDepartment(department);
        return ResponseEntity.ok(books);
    }
}


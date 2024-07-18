package com.example.liberarymanagementsystem.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.liberarymanagementsystem.Entity.Book;
import com.example.liberarymanagementsystem.Repositorys.BookRepository;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private static BookRepository bookRepository;

    public Book addBook(Book book) {
        if (bookRepository.existsById(book.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        }
        return bookRepository.save(book);
    }

    public static void removeBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new IllegalArgumentException("Book with this ISBN does not exist.");
        }
        bookRepository.deleteById(isbn);
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public List<Book> listBooksByDepartment(String department) {
        return bookRepository.findByDepartmentIgnoreCase(department);
    }
}


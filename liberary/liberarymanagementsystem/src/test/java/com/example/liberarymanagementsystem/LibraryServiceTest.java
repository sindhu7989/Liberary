package com.example.liberarymanagementsystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.liberarymanagementsystem.Entity.Book;
import com.example.liberarymanagementsystem.Repositorys.BookRepository;
import com.example.liberarymanagementsystem.Services.LibraryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class LibraryServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        book1 = new Book("Test Title 1", "Test Author 1", "1234567890", "Test Genre", 2021, "Test Department");
        book2 = new Book("Test Title 2", "Test Author 2", "0987654321", "Test Genre", 2022, "Test Department");
    }

    @Test
    public void testAddBook() {
        when(bookRepository.existsById(book1.getIsbn())).thenReturn(false);
        when(bookRepository.save(book1)).thenReturn(book1);

        Book savedBook = libraryService.addBook(book1);

        assertEquals(book1, savedBook);
        verify(bookRepository, times(1)).save(book1);
    }

    @Test
    public void testAddBookAlreadyExists() {
        when(bookRepository.existsById(book1.getIsbn())).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            libraryService.addBook(book1);
        });

        String expectedMessage = "Book with this ISBN already exists.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testRemoveBook() {
        when(bookRepository.existsById(book1.getIsbn())).thenReturn(true);

        libraryService.removeBook(book1.getIsbn());

        verify(bookRepository, times(1)).deleteById(book1.getIsbn());
    }

    @Test
    public void testRemoveBookNotExists() {
        when(bookRepository.existsById(book1.getIsbn())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            libraryService.removeBook(book1.getIsbn());
        });

        String expectedMessage = "Book with this ISBN does not exist.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindBooksByTitle() {
        when(bookRepository.findByTitleIgnoreCase("Test Title 1")).thenReturn(Arrays.asList(book1));

        List<Book> books = libraryService.findBooksByTitle("Test Title 1");

        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));
    }

    @Test
    public void testFindBooksByAuthor() {
        when(bookRepository.findByAuthorIgnoreCase("Test Author 1")).thenReturn(Arrays.asList(book1));

        List<Book> books = libraryService.findBooksByAuthor("Test Author 1");

        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));
    }

    @Test
    public void testListAllBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = libraryService.listAllBooks();

        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }

    @Test
    public void testListAvailableBooks() {
        book2.setAvailable(false);
        when(bookRepository.findByAvailableTrue()).thenReturn(Arrays.asList(book1));

        List<Book> books = libraryService.listAvailableBooks();

        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));
    }

    @Test
    public void testListBooksByDepartment() {
        when(bookRepository.findByDepartmentIgnoreCase("Test Department")).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = libraryService.listBooksByDepartment("Test Department");

        assertEquals(2, books.size());
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
    }
}


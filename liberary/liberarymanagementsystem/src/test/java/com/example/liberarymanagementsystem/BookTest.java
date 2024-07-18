package com.example.liberarymanagementsystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.liberarymanagementsystem.Entity.Book;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Test Title", "Test Author", "1234567890", "Test Genre", 2021, "Test Department");
    }

    @Test
    public void testGetters() {
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("1234567890", book.getIsbn());
        assertEquals("Test Genre", book.getGenre());
        assertEquals(2021, book.getPublicationYear());
        assertEquals("Test Department", book.getDepartment());
        assertTrue(book.isAvailable());
    }

    @Test
    public void testSetters() {
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setIsbn("0987654321");
        book.setGenre("New Genre");
        book.setPublicationYear(2022);
        book.setDepartment("New Department");
        book.setAvailable(false);

        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("0987654321", book.getIsbn());
        assertEquals("New Genre", book.getGenre());
        assertEquals(2022, book.getPublicationYear());
        assertEquals("New Department", book.getDepartment());
        assertFalse(book.isAvailable());
    }
}


package com.example.liberarymanagementsystem.Entity;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private int publicationYear;
    private String department;
    private boolean available;

    public Book(String title, String author, String isbn, String genre, int publicationYear, String department) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.department = department;
        this.available = true; 
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", publicationYear=" + publicationYear +
                ", department='" + department + '\'' +
                ", available=" + available +
                '}';
    }
}


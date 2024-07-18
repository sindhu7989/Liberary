package com.example.liberarymanagementsystem.Repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.liberarymanagementsystem.Entity.Book;

public interface BookRepository extends JpaRepository<Book,String>{

	List<Book> findByTitleIgnoreCase(String title);

	List<Book> findByAuthorIgnoreCase(String author);

	List<Book> findByAvailableTrue();

	List<Book> findByDepartmentIgnoreCase(String department);

}

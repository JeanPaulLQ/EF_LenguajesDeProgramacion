package com.example.biblioteca.repository;

import com.example.biblioteca.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "SELECT b.* FROM Book b WHERE b.date BETWEEN DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH) AND CURRENT_DATE()",nativeQuery = true)
    List<Book> getBooks();
}

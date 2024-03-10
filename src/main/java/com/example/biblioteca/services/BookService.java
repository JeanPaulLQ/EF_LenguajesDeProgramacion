package com.example.biblioteca.services;

import com.example.biblioteca.models.Book;
import com.example.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    public Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
    public List<Book> getAllLastBooks(){
        return bookRepository.getBooks();
    }
    public void createBook(Book book){
        bookRepository.save(book);
    }
}

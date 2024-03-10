package com.example.biblioteca.controllers;

import com.example.biblioteca.models.Book;
import com.example.biblioteca.models.Genre;
import com.example.biblioteca.services.BookService;
import com.example.biblioteca.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/listBooks")
    public String listBooks(Model model){
        List<Book> listBooks = bookService.getAllBooks();
        model.addAttribute("listBooks",listBooks);
        return "listBooks";
    }
    @GetMapping("/registerBook")
    public String register(Model model){
        List<Genre> list = genreService.getGenres();
        model.addAttribute("listGenre",list);
        return "registerBook";
    }
    @PostMapping("/register")
    public String createBook(@RequestParam("name")String name,
                             @RequestParam("author")String author,
                             @RequestParam("date")String date,
                             @RequestParam("genre")Long genre,
                             Model model){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,format);
        book.setDate(localDate);
        Genre genre1 = genreService.getGenreById(genre);
        book.setIdGenre(genre1);
        bookService.createBook(book);
        model.addAttribute("listBooks",bookService.getAllBooks());
        return "redirect:/listBooks";
    }
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book",book);
        List<Genre> list = genreService.getGenres();
        model.addAttribute("listGenre",list);
        return "editBook";
    }
    @PostMapping("/edit")
    public String editBook(@RequestParam("id")Long id,
                           @RequestParam("name")String name,
                           @RequestParam("author")String author,
                           @RequestParam("date")String date,
                           @RequestParam("genre")Long genre,
                           Model model){
        Book book = bookService.getBookById(id);
        book.setName(name);
        book.setAuthor(author);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,format);
        book.setDate(localDate);
        Genre genre1 = genreService.getGenreById(genre);
        book.setIdGenre(genre1);
        bookService.createBook(book);

        model.addAttribute("listBooks",bookService.getAllBooks());
        return "redirect:/listBooks";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id,Model model){
        bookService.deleteBook(id);
        model.addAttribute("listBooks",bookService.getAllBooks());
        return "listBooks";
    }
}

package com.example.biblioteca.models;

import jakarta.persistence.*;;

import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4,max = 60)
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "author",nullable = false)
    @Size(min = 4,max = 60)
    private String author;
    @Column(name = "date",nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "idGenre",nullable = false)
    private Genre idGenre;
    public String formatedDate(){
        String formated = date.getDayOfMonth()+"-"+date.getMonthValue()+"-"+date.getYear();
        return formated;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Genre getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Genre idGenre) {
        this.idGenre = idGenre;
    }


}

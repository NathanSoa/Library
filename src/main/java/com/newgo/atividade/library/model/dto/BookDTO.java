package com.newgo.atividade.library.model.dto;

import com.newgo.atividade.library.model.Author;
import com.newgo.atividade.library.model.Book;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDTO {

    private String title;
    private LocalDate publicationDate;
    private Integer edition;
    private Set<String> authors;
    private String isbn;
    private String publisher;

    public BookDTO() {

    }

    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.publicationDate = book.getPublicationDate();
        this.edition = book.getEdition();
        this.authors = book.getAuthors()
                                .stream()
                                .map(Author::getFullName)
                                .collect(Collectors.toSet());
        this.isbn = book.getIsbn().getIsbn();
        this.publisher = book.getPublisher();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}

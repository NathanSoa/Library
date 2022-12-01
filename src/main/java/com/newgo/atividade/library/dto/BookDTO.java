package com.newgo.atividade.library.dto;

import com.newgo.atividade.library.model.valueobject.Name;

import java.time.LocalDate;
import java.util.Set;
public class BookDTO {

    private String title;
    private LocalDate publicationDate;
    private Integer edition;
    private Set<Name> authors;
    private String isbn;
    private String publisher;

    public BookDTO() {

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

    public Set<Name> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Name> authors) {
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

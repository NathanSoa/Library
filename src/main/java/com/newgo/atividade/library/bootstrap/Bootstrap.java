package com.newgo.atividade.library.bootstrap;

import com.newgo.atividade.library.model.Author;
import com.newgo.atividade.library.model.Book;
import com.newgo.atividade.library.model.valueobject.ISBN;
import com.newgo.atividade.library.service.AuthorService;
import com.newgo.atividade.library.service.BookService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class Bootstrap implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;

    public Bootstrap(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setFirstName("Abel");
        author.setLastName("Ferreira");
        author.setBirthdate(LocalDate.of(1980, 10, 10));

        authorService.save(author);

        Book book = new Book();
        book.setEdition(1);
        book.setIsbn(new ISBN("978-1-56619-909-4"));
        book.setTitle("Cabeça fria, coração quente");
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);
        book.setPublicationDate(LocalDate.now());
        book.setPublisher("Publisher");

        bookService.save(book);
    }
}

package com.newgo.atividade.library.controller;

import com.newgo.atividade.library.model.Book;
import com.newgo.atividade.library.model.dto.BookDTO;
import com.newgo.atividade.library.service.BookService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return book == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(new BookDTO(book));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BookDTO> findAll(Pageable pageable){
        return bookService
                .findAll(pageable)
                .map(BookDTO::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Book book) {
        bookService.save(book);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.update(id, book);
        return ResponseEntity.ok(new BookDTO(updatedBook));
    }
}

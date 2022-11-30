package com.newgo.atividade.library.controller;

import com.newgo.atividade.library.model.Author;
import com.newgo.atividade.library.service.AuthorService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Author> findAll(Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        Author author = authorService.findById(id);
        return author == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(author);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Author author) {
        authorService.save(author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.update(id, author);
        return ResponseEntity.ok(updatedAuthor);
    }
}

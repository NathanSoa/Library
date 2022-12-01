package com.newgo.atividade.library.controller;

import com.newgo.atividade.library.dto.AuthorDTO;
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
    public Page<AuthorDTO> findAll(Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id){
        AuthorDTO author = authorService.findById(id);
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
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody Author author) {
        AuthorDTO updatedAuthor = authorService.update(id, author);
        return updatedAuthor == null ? ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build() : ResponseEntity.ok(updatedAuthor);
    }
}

package com.newgo.atividade.library.service.implementation;

import com.newgo.atividade.library.model.Author;
import com.newgo.atividade.library.repository.AuthorRepository;
import com.newgo.atividade.library.service.AuthorService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void save(Author object) {
        authorRepository.save(object);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author update(Long id, Author object) {
        Author author = findById(id);
        BeanUtils.copyProperties(object, author, "id");
        save(author);
        return author;
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean exists(Set<Author> authors) {
        return authors.stream()
                    .allMatch(author -> existById(author.getId()));
    }

    @Override
    public boolean existById(Long id) {
        return authorRepository.existsById(id);
    }
}

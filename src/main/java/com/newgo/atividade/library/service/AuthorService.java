package com.newgo.atividade.library.service;

import com.newgo.atividade.library.exception.IllegalAuthorDeleteOperationException;
import com.newgo.atividade.library.exception.InvalidAuthorException;
import com.newgo.atividade.library.model.Author;
import com.newgo.atividade.library.dto.AuthorDTO;
import com.newgo.atividade.library.repository.AuthorRepository;

import org.modelmapper.ModelMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public void save(Author object) {
        authorRepository.save(object);
    }

    public AuthorDTO findById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.isPresent() ? modelMapper.map(author.get(), AuthorDTO.class) : null;
    }

    public Page<AuthorDTO> findAll(Pageable pageable) {
        return authorRepository
                .findAll(pageable)
                .map(each -> modelMapper.map(each, AuthorDTO.class));
    }

    public AuthorDTO update(Long id, Author object) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if(!authorOptional.isPresent()) {
            return null;
        }

        Author author = authorOptional.get();
        BeanUtils.copyProperties(object, author, "id", "books");
        save(author);

        return modelMapper.map(author, AuthorDTO.class);
    }

    public void deleteById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if(!authorOptional.isPresent()) {
            throw new InvalidAuthorException("Author does not exists");
        }

        Author author = authorOptional.get();

        if(author.getBooks().size() > 0)
            throw new IllegalAuthorDeleteOperationException("Cannot delete any author that have books registered!");

        authorRepository.deleteById(id);
    }

    public boolean exists(Set<Author> authors) {
        return authors.stream()
                .map(Author::getId)
                .allMatch(this::existsById);
    }

    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }
}

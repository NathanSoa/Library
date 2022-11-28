package com.newgo.atividade.library.service;

import com.newgo.atividade.library.model.Author;

import java.util.Set;

public interface AuthorService extends CrudService<Author, Long> {

    boolean exists(Set<Author> authors);
    boolean existById(Long id);
}

package com.newgo.atividade.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {

    void save(T object);

    T findById(ID id);

    Page<T> findAll(Pageable pageable);

    T update(ID id, T object);

    void deleteById(ID id);
}


package com.newgo.atividade.library.service.implementation;

import com.newgo.atividade.library.exception.InvalidAuthorException;
import com.newgo.atividade.library.model.Book;
import com.newgo.atividade.library.repository.BookRepository;
import com.newgo.atividade.library.service.AuthorService;
import com.newgo.atividade.library.service.BookService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public void save(Book object) {
        if(authorService.exists(object.getAuthors())){
            bookRepository.save(object);
            return;
        }

        throw new InvalidAuthorException("An invalid author was sent!");
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book update(Long id, Book object) {
        Book book = findById(id);
        BeanUtils.copyProperties(object, book, "id");
        save(book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

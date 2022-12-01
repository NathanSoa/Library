package com.newgo.atividade.library.service;

import com.newgo.atividade.library.exception.InvalidAuthorException;
import com.newgo.atividade.library.model.Book;
import com.newgo.atividade.library.dto.BookDTO;
import com.newgo.atividade.library.repository.BookRepository;

import org.modelmapper.ModelMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, AuthorService authorService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    public void save(Book object) {
        if(authorService.exists(object.getAuthors()) && object.getAuthors().size() >= 1){
            bookRepository.save(object);
            return;
        }

        throw new InvalidAuthorException("An invalid author was sent!");
    }

    public BookDTO findById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? modelMapper.map(book.get(), BookDTO.class) : null;
    }

    public Page<BookDTO> findAll(Pageable pageable) {
        return bookRepository
                .findAll(pageable)
                .map(each -> modelMapper.map(each, BookDTO.class));
    }

    public BookDTO update(Long id, Book object) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if(!bookOptional.isPresent()) {
            return null;
        }

        Book book = bookOptional.get();
        BeanUtils.copyProperties(object, book, "id");
        save(book);

        return modelMapper.map(book, BookDTO.class);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}

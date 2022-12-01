package com.newgo.atividade.library.config;

import com.newgo.atividade.library.dto.AuthorDTO;
import com.newgo.atividade.library.model.Author;
import com.newgo.atividade.library.model.Book;
import com.newgo.atividade.library.dto.BookDTO;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.stream.Collectors;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<Set<Book>, Set<String>> bookToTitle = context -> context.getSource().stream().map(Book::getTitle).collect(Collectors.toSet());

        modelMapper.typeMap(Book.class, BookDTO.class)
                .addMappings(src -> {
                    src.<String>map(book -> book.getIsbn().getIsbn(), BookDTO::setIsbn);
                });

        modelMapper.typeMap(Author.class, AuthorDTO.class)
                .addMappings(src -> {
                    src.using(bookToTitle);
                    src.<Set<String>>map(Author::getBooks, AuthorDTO::setBooks);
                    src.map(author -> author.getName().getFirstName(), AuthorDTO::setFirstName);
                    src.map(author -> author.getName().getLastName(), AuthorDTO::setLastName);
                });
        return modelMapper;
    }
}

package com.newgo.atividade.library.model.valueobject;

import javax.persistence.Embeddable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class ISBN {

    private String isbn;

    public ISBN() {

    }

    public ISBN(String isbn) {
        setIsbn(isbn);
    }

    private boolean validate(String value) {
        Pattern pattern = Pattern.compile("([0-9]{3}-?[0-9]{1}-?[0-9]{5}-?[0-9]{3}-?[0-9]{1})|([0-9]{1}-?[0-9]{5}-?[0-9]{3}-?[0-9]{1})");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(!validate(isbn))
            throw new IllegalArgumentException("ISBN should be in valid format!");
        this.isbn = isbn;
    }
}

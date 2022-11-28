package com.newgo.atividade.library.model.valueobject;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
public class ISBN {

    private String number;

    public ISBN() {

    }

    private boolean validate(String value) {
        Pattern pattern = Pattern.compile("([0-9]{3}-?[0-9]{1}-?[0-9]{5}-?[0-9]{3}-?[0-9]{1})|([0-9]{1}-?[0-9]{5}-?[0-9]{3}-?[0-9]{1})");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if(!validate(number))
            throw new IllegalArgumentException("ISBN should be in valid format!");
        this.number = number;
    }
}

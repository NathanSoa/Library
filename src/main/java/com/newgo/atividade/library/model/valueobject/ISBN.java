package com.newgo.atividade.library.model.valueobject;

import javax.persistence.Embeddable;

@Embeddable
public class ISBN {

    private String number;

    public ISBN() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

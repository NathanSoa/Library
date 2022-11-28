package com.newgo.atividade.library.model;

import javax.persistence.Embeddable;

@Embeddable
public class Publisher {

    public Publisher() {

    }

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    private String publisherName;

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String name) {
        this.publisherName = name;
    }
}

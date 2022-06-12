package com.podvazhuk.gallery.domain;

public class Artist {
    private String id;
    private String name;
    private String country;

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }
}

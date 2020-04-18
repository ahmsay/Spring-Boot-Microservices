package com.ravager.moviecatalogservice.entity;

public class Catalog {

    private String name;
    private String description;
    private int rating;

    public Catalog(final String name, final String description, final int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(final int rating) {
        this.rating = rating;
    }
}

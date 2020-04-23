package com.ravager.moviecatalogservice.entity;

public class Movie {

    private String movieId;
    private String name;
    private String description;

    public Movie() {}

    public Movie(final String movieId, final String name, final String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(final String movieId) {
        this.movieId = movieId;
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
}

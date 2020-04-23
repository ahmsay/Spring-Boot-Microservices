package com.ravager.movieinfoservice.entity;

public class MovieSummary {

    private String id;
    private String title;
    private String overview;

    public MovieSummary() {}

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(final String overview) {
        this.overview = overview;
    }
}

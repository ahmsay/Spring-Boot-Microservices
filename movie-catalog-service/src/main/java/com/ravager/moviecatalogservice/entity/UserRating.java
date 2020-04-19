package com.ravager.moviecatalogservice.entity;

import java.util.List;

public class UserRating {

    private List<Rating> userRatings;

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(final List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}

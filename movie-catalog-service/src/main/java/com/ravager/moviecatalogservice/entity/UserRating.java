package com.ravager.moviecatalogservice.entity;

import java.util.List;

public class UserRating {

    private String userId;

    private List<Rating> userRatings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(final List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}

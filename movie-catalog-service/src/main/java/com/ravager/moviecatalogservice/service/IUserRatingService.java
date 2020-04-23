package com.ravager.moviecatalogservice.service;

import com.ravager.moviecatalogservice.entity.UserRating;

public interface IUserRatingService {

    UserRating getUserRating(String userId);
}

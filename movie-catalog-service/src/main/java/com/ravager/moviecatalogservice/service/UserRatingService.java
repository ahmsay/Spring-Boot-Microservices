package com.ravager.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ravager.moviecatalogservice.entity.Rating;
import com.ravager.moviecatalogservice.entity.UserRating;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService implements IUserRatingService {

    private final RestTemplate restTemplate;

    public UserRatingService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(final String userId) {
        return restTemplate.getForObject("http://ratings-data-service/rating/user/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(final String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRatings(Arrays.asList(new Rating("0", 0)));
        return userRating;
    }
}

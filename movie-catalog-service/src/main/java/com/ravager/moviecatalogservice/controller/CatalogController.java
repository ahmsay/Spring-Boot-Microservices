package com.ravager.moviecatalogservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ravager.moviecatalogservice.entity.Catalog;
import com.ravager.moviecatalogservice.entity.Movie;
import com.ravager.moviecatalogservice.entity.Rating;
import com.ravager.moviecatalogservice.entity.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final RestTemplate restTemplate;

    public CatalogController(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{userId}")
    public List<Catalog> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = getUserRating(userId);

        return userRating.getUserRatings().stream()
                .map(rating -> getCatalog(rating))
                .collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    private UserRating getUserRating(final String userId) {
        return restTemplate.getForObject("http://ratings-data-service/rating/user/" + userId, UserRating.class);
    }

    private UserRating getFallbackUserRating(final String userId) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRatings(Arrays.asList(new Rating("0", 0)));
        return userRating;
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    private Catalog getCatalog(final Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new Catalog(movie.getName(), movie.getDescription(), rating.getRating());
    }

    private Catalog getFallbackCatalog(final Rating rating) {
        return new Catalog("Movie not found", "Description not found", rating.getRating());
    }
}

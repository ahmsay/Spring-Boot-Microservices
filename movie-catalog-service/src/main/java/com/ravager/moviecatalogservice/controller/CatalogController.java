package com.ravager.moviecatalogservice.controller;

import com.ravager.moviecatalogservice.entity.Catalog;
import com.ravager.moviecatalogservice.entity.Movie;
import com.ravager.moviecatalogservice.entity.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/rating/user/" + userId, UserRating.class);

        return userRating.getUserRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new Catalog(movie.getName(), movie.getDescription(), rating.getRating());
        }).collect(Collectors.toList());
    }
}

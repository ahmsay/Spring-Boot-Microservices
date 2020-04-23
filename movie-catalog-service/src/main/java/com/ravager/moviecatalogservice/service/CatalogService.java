package com.ravager.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ravager.moviecatalogservice.entity.Catalog;
import com.ravager.moviecatalogservice.entity.Movie;
import com.ravager.moviecatalogservice.entity.Rating;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogService implements ICatalogService {

    private final RestTemplate restTemplate;

    public CatalogService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getFallbackCatalog", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public Catalog getCatalog(final Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        return new Catalog(movie.getName(), movie.getDescription(), rating.getRating());
    }

    private Catalog getFallbackCatalog(final Rating rating) {
        return new Catalog("Movie not found", "Description not found", rating.getRating());
    }
}

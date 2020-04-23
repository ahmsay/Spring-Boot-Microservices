package com.ravager.moviecatalogservice.controller;

import com.ravager.moviecatalogservice.entity.Catalog;
import com.ravager.moviecatalogservice.entity.UserRating;
import com.ravager.moviecatalogservice.service.ICatalogService;
import com.ravager.moviecatalogservice.service.IUserRatingService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final IUserRatingService userRatingService;
    private final ICatalogService catalogService;

    public CatalogController(final IUserRatingService userRatingService, final ICatalogService catalogService) {
        this.userRatingService = userRatingService;
        this.catalogService = catalogService;
    }

    @RequestMapping("/{userId}")
    public List<Catalog> getCatalog(@PathVariable("userId") final String userId) {
        UserRating userRating = userRatingService.getUserRating(userId);

        return userRating.getUserRatings().stream()
                .map(catalogService::getCatalog)
                .collect(Collectors.toList());
    }
}

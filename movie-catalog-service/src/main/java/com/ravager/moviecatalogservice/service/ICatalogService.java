package com.ravager.moviecatalogservice.service;

import com.ravager.moviecatalogservice.entity.Catalog;
import com.ravager.moviecatalogservice.entity.Rating;

public interface ICatalogService {

    Catalog getCatalog(Rating rating);
}

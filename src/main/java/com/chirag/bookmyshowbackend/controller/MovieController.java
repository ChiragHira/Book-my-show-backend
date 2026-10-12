package com.chirag.bookmyshowbackend.controller;

import com.chirag.bookmyshowbackend.dto.MovieResponse;
import com.chirag.bookmyshowbackend.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final CatalogService catalogService;

    public MovieController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<MovieResponse> getAllMovies(){
        return catalogService.getAllMovies();
    }
}

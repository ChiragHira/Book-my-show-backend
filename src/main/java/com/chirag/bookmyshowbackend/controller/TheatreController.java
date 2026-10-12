package com.chirag.bookmyshowbackend.controller;

import com.chirag.bookmyshowbackend.dto.TheatreResponse;
import com.chirag.bookmyshowbackend.service.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theatres")
public class TheatreController {

    private final CatalogService catalogService;

    public TheatreController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<TheatreResponse> getAllTheatres(@RequestParam String city){
        return catalogService.theatres(city);
    }
}

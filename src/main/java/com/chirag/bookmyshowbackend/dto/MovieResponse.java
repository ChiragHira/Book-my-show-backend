package com.chirag.bookmyshowbackend.dto;

import com.chirag.bookmyshowbackend.entity.Movie;

public record MovieResponse(
        Long id,String title,String language,String genre,int durationMinutes,
        String certification,String description,String trailerUrl,boolean active){

    public static MovieResponse from(Movie movie){
        return new MovieResponse(movie.getId(),movie.getTitle(),movie.getLanguage(),movie.getGenre(),movie.getDurationInMinutes(),
                movie.getCertification(),movie.getDescription(),movie.getTrailerUrl(),movie.isActive());
    }

}

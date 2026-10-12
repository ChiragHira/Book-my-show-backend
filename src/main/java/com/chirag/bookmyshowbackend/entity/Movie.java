package com.chirag.bookmyshowbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movies",uniqueConstraints = @UniqueConstraint(name = "uk-movie-title", columnNames = "title"))
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String language;

    private String  title;

    private int duration;

    private String genre;

    private Integer durationInMinutes;

    private String certification;

    private String description;

    private String trailerUrl;

    private boolean active = true;

    public Movie() {}

    public Movie(String language, String title, int duration, String genre, Integer durationInMinutes, String certification, String description, String trailerUrl, boolean active) {
        this.language = language;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
        this.certification = certification;
        this.description = description;
        this.trailerUrl = trailerUrl;
        this.active = active;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }
}

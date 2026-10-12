package com.chirag.bookmyshowbackend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table( name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Theatre theatre;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private BigDecimal ticketPrice;

    private int totalSeats;

    private int availableSeats;

    private boolean active = true;

    @Version
    private long version;

    public Show() {}

    public Show(Movie movie, Theatre theatre, LocalDateTime startAt, LocalDateTime endAt, int totalSeats, BigDecimal ticketPrice, boolean active, long version) {
        this.movie = movie;
        this.theatre = theatre;
        this.startAt = startAt;
        this.endAt = endAt;
        this.totalSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.availableSeats = totalSeats;
        this.active = active;
        this.version = version;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void reserveSeats(int seats) {

        if( seats == 0 || seats > availableSeats) {
            throw new IllegalArgumentException("Not Enough Seats Available");
        }
        availableSeats -= seats;
    }

    public void releaseSeats(int seats) {
        availableSeats = Math.min(availableSeats + seats, totalSeats);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
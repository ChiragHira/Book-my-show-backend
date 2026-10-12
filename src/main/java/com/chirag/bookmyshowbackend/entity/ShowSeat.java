package com.chirag.bookmyshowbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "show_seats", uniqueConstraints = @UniqueConstraint(name = "uk-show-seat", columnNames = {"show_id", "seat_label"}))
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Show show;

    private String  seatLabel;

    private boolean reserved;

    public ShowSeat() {}

    public ShowSeat(Show show, String seatLabel) {
        this.show = show;
        this.seatLabel = seatLabel;
    }

    public String getSeatLabel() {
        return seatLabel;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void reserve() {
        this.reserved = true;
    }

    public void release() {
        this.reserved = false;
    }

    public Long getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }
}

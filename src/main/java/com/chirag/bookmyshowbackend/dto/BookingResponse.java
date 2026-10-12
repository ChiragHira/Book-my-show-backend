package com.chirag.bookmyshowbackend.dto;

import com.chirag.bookmyshowbackend.entity.Booking;
import com.chirag.bookmyshowbackend.entity.BookingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record BookingResponse(Long id, Long ShowId, Long profileId, String movieTitle, String TheatreName,
                              String customerName, String customerEmail, String customerPhone, List<String> seatLabels,
                              BigDecimal TotalAmount, BookingStatus status, LocalDateTime bookedAt) {

    public static BookingResponse from (Booking booking){

        return new BookingResponse(booking.getId(),booking.getShow().getId(),
                booking.getCustomer()==null ? null : booking.getCustomer().getId(),
                booking.getShow().getMovie().getTitle(),booking.getShow().getTheatre().getName(),
                booking.getCustomerName(),booking.getCustomerEmail(),booking.getCustomerPhone(),
                booking.getSeatLabels(),booking.getTotalPrice(),booking.getStatus(),booking.getBookedAt());

    }
}

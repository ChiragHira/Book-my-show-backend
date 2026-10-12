package com.chirag.bookmyshowbackend.controller;

import com.chirag.bookmyshowbackend.dto.BookingResponse;
import com.chirag.bookmyshowbackend.dto.CreateBookingRequest;
import com.chirag.bookmyshowbackend.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/shows/{showId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse book(@PathVariable Long showId , @RequestBody CreateBookingRequest request){
        System.out.println(request);
        return bookingService.book(showId, request);
    }

    @PostMapping("/cancel/{bookingId}")
    public BookingResponse cancel(@PathVariable Long bookingId, @RequestParam Long profileId){
        return bookingService.cancel(bookingId,profileId);
    }

    @GetMapping("/{bookingId}")
    public BookingResponse find(@PathVariable Long bookingId){
        return bookingService.find(bookingId);
    }
}

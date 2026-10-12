package com.chirag.bookmyshowbackend.controller;

import com.chirag.bookmyshowbackend.dto.BookingResponse;
import com.chirag.bookmyshowbackend.dto.CreateProfileRequest;
import com.chirag.bookmyshowbackend.dto.ProfileResponse;
import com.chirag.bookmyshowbackend.service.BookingService;
import com.chirag.bookmyshowbackend.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final BookingService bookingService;

    public ProfileController(ProfileService profileService, BookingService bookingService) {
        this.profileService = profileService;
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(@Valid @RequestBody CreateProfileRequest request){
        return profileService.create(request);
    }

    @PostMapping("/login")
    public ProfileResponse login(@RequestParam String identifier){
        return profileService.login(identifier);
    }

    @GetMapping("/bookings/{profileId}")
    public List<BookingResponse> findByProfileId(@PathVariable Long profileId){
        return bookingService.findByProfileId(profileId);
    }



}

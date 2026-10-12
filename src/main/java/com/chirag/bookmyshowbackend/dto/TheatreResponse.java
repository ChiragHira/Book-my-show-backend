package com.chirag.bookmyshowbackend.dto;

import com.chirag.bookmyshowbackend.entity.Theatre;

public record TheatreResponse(Long id, String name, String city, String address) {

    public static TheatreResponse from(Theatre theatre){
        return new TheatreResponse(theatre.getId(),theatre.getName(),theatre.getCity(),theatre.getAddress());
    }
}

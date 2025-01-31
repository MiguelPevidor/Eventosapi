package io.github.miguel.eventostec.dto;

import io.github.miguel.eventostec.model.Address;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record EventRequestDTO (

        String title,
        String description,
        String state,
        String city,
        Long date,
        Boolean remote,
        String eventUrl,
        MultipartFile image
){

}

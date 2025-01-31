package io.github.miguel.eventostec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


public record EventResponseDTO(
     UUID id,
     String title,
     String description,
     Date date,
     AddressResponseDTO address,
     Boolean remote,
     String eventUrl,
     String imgUrl
     ){
}

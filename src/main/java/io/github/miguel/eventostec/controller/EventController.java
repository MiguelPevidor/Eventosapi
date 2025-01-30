package io.github.miguel.eventostec.controller;

import io.github.miguel.eventostec.dto.EventRequestDTO;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.service.EventService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("api/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping
    public ResponseEntity<Event> createEvent(
                         @RequestParam("title") String title,
                         @RequestParam("description") String description,
                         @RequestParam(value = "state", required = false) String state,
                         @RequestParam(value = "city", required = false) String city,
                         @RequestParam(value = "date") Long date,
                         @RequestParam(value = "remote") Boolean remote,
                         @RequestParam(value = "eventUrl") String eventUrl,
                         @RequestParam(value = "image", required = false) MultipartFile image){
        EventRequestDTO dto = new EventRequestDTO(title,description,state,city,date,remote,eventUrl,image);
        Event event = service.createEvent(dto);
        return ResponseEntity.ok(event);
    }
}

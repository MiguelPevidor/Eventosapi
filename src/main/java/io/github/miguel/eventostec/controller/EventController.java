package io.github.miguel.eventostec.controller;

import io.github.miguel.eventostec.dto.EventDetailsDTO;
import io.github.miguel.eventostec.dto.EventRequestDTO;
import io.github.miguel.eventostec.dto.EventResponseDTO;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;


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

    //Busca paginada de todos os eventos que ainda não aconteceram
    @GetMapping("/")
    public ResponseEntity<Page<EventResponseDTO>> getAllEvents(
                                               @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                               @RequestParam(value = "size", defaultValue = "3", required = false) Integer size,
                                               @RequestParam(value = "orderBy", defaultValue = "title", required = false) String orderBy,
                                               @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<EventResponseDTO> allEvents = service.getAllEvents(page, size, orderBy, direction);

        return ResponseEntity.ok(allEvents);
    }

    //Busca paginada de eventos, através de parametros passados
    @GetMapping("/filter")
    public ResponseEntity<Page<EventResponseDTO>> getFilteredEvents(
                                                @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                @RequestParam(value = "size", defaultValue = "3", required = false) Integer size,
                                                @RequestParam(value = "orderBy", defaultValue = "title", required = false) String orderBy,
                                                @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
                                                @RequestParam(value = "title", defaultValue = "", required = false) String title,
                                                @RequestParam(value = "city", defaultValue = "", required = false) String city,
                                                @RequestParam(value = "state", defaultValue = "", required = false) String state,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){

        Page<EventResponseDTO> filteredEvents = service.getFilteredEvents(page,size,orderBy,direction, title, city, state, startDate, endDate );
        return ResponseEntity.ok(filteredEvents);
    }

    @GetMapping("/{event_id}")
    public ResponseEntity<EventDetailsDTO> getEventDetails(@PathVariable("event_id")UUID eventId){
        EventDetailsDTO event = service.getEventDetails(eventId);
        if (event != null){
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }
}

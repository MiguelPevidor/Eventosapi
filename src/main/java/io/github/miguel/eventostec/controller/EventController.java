package io.github.miguel.eventostec.controller;

import io.github.miguel.eventostec.dto.EventDetailsDTO;
import io.github.miguel.eventostec.dto.EventRequestDTO;
import io.github.miguel.eventostec.dto.EventResponseDTO;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Eventos")
public class EventController {

    private final EventService service;


    @PostMapping
    @Operation(summary = "Cadastrar" , description = "Cadastra Eventos")
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
    @Operation(summary = "Buscar eventos futuros", description = "Retorna uma página com eventos que ainda não ocorreram.")
    public ResponseEntity<Page<EventResponseDTO>> getAllEvents(
            @Parameter(description = "Número da página (padrão: 0)") @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @Parameter(description = "Tamanho da página (padrão: 3)")  @RequestParam(value = "size", defaultValue = "3", required = false) Integer size,
            @Parameter(description = "Campo para ordenação (padrão: title)") @RequestParam(value = "orderBy", defaultValue = "title", required = false) String orderBy,
            @Parameter(description = "Direção da ordenação (ASC ou DESC, padrão: ASC)") @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction){
        Page<EventResponseDTO> allEvents = service.getAllEvents(page, size, orderBy, direction);

        return ResponseEntity.ok(allEvents);
    }

    //Busca paginada de eventos, através de parametros passados
    @GetMapping("/filter")
    @Operation(summary = "Filtrar eventos", description = "Retorna eventos filtrados por título, cidade, estado ou intervalo de datas.")
    public ResponseEntity<Page<EventResponseDTO>> getFilteredEvents(
            @Parameter(description = "Número da página (padrão: 0)")
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @Parameter(description = "Tamanho da página (padrão: 3)")
            @RequestParam(value = "size", defaultValue = "3", required = false) Integer size,
            @Parameter(description = "Campo para ordenação (padrão: title)")
            @RequestParam(value = "orderBy", defaultValue = "title", required = false) String orderBy,
            @Parameter(description = "Direção da ordenação (ASC ou DESC, padrão: ASC)")
            @RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction,
            @Parameter(description = "Filtrar pelo título do evento")
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @Parameter(description = "Filtrar pela cidade")
            @RequestParam(value = "city", defaultValue = "", required = false) String city,
            @Parameter(description = "Filtrar pelo estado")
            @RequestParam(value = "state", defaultValue = "", required = false) String state,
            @Parameter(description = "Data de início no formato ISO (yyyy-MM-dd)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @Parameter(description = "Data de fim no formato ISO (yyyy-MM-dd)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        Page<EventResponseDTO> filteredEvents = service.getFilteredEvents(page,size,orderBy,direction, title, city, state, startDate, endDate );
        return ResponseEntity.ok(filteredEvents);
    }

    @GetMapping("/{event_id}")
    @Operation(summary = "Obter detalhes do evento", description = "Retorna os detalhes de um evento específico pelo seu ID.")
    public ResponseEntity<EventDetailsDTO> getEventDetails(@Parameter(description = "ID do evento") @PathVariable("event_id") UUID eventId) {
        EventDetailsDTO event = service.getEventDetails(eventId);
        if (event != null){
            return ResponseEntity.ok(event);
        }
        return ResponseEntity.notFound().build();
    }
}

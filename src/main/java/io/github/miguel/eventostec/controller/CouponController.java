package io.github.miguel.eventostec.controller;

import io.github.miguel.eventostec.dto.CouponRequestDTO;
import io.github.miguel.eventostec.dto.CouponResponseDTO;
import io.github.miguel.eventostec.model.Coupon;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.service.CouponService;
import io.github.miguel.eventostec.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
@Tag(name = "Cupons")
public class CouponController {

    private final CouponService service;


    @PostMapping("/event/{event_id}")
    @Operation(summary = "Cadastrar", description = "Cadastra e associa Cupons á um evento")
    public ResponseEntity<CouponResponseDTO> createCoupon(@PathVariable("event_id") UUID eventId,
                                                          @RequestBody CouponRequestDTO dto) {
        CouponResponseDTO coupon = service.createCoupon(eventId, dto);
        return ResponseEntity.ok(coupon);
    }
}

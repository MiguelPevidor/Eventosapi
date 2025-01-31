package io.github.miguel.eventostec.service;

import io.github.miguel.eventostec.dto.CouponRequestDTO;
import io.github.miguel.eventostec.dto.CouponResponseDTO;
import io.github.miguel.eventostec.mappers.CouponMapper;
import io.github.miguel.eventostec.model.Coupon;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.repository.CouponRepository;
import io.github.miguel.eventostec.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository repository;
    private final EventRepository eventRepository;
    private final CouponMapper mapper;

    public CouponResponseDTO createCoupon(UUID eventId, CouponRequestDTO dto){
        //Procura o evento relacionado ao cupon
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = mapper.toEntity(dto);
        coupon.setEvent(event);
        repository.save(coupon);
        return mapper.toDto(coupon);
    }
}

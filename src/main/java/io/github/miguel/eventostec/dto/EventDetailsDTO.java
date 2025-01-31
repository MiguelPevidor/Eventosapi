package io.github.miguel.eventostec.dto;

import io.github.miguel.eventostec.model.Coupon;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record EventDetailsDTO(
        UUID id,
        String title,
        String description,
        Date date,
        AddressResponseDTO address,
        Boolean remote,
        String eventUrl,
        String imgUrl,
        List<CouponResponseDTO> coupons
) {
}

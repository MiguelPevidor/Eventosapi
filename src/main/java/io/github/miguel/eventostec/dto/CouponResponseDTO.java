package io.github.miguel.eventostec.dto;

import java.util.Date;

public record CouponResponseDTO(
        String code,
        Integer discount,
        Date valid
) {

}

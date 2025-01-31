package io.github.miguel.eventostec.mappers;

import io.github.miguel.eventostec.dto.CouponRequestDTO;
import io.github.miguel.eventostec.dto.CouponResponseDTO;
import io.github.miguel.eventostec.model.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CouponMapper {


    @Mapping(source = "valid",target = "valid", qualifiedByName = "longToDate")
    Coupon toEntity(CouponRequestDTO dto);

    CouponResponseDTO toDto(Coupon coupon);

    List<CouponResponseDTO> toDto(List<Coupon> coupons);

    @Named("longToDate")
    static Date longToDate(Long date){
        if(date == null){
            return null;
        }
        return new Date(date);
    }
}

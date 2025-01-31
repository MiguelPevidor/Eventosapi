package io.github.miguel.eventostec.mappers;

import io.github.miguel.eventostec.dto.*;
import io.github.miguel.eventostec.model.Address;
import io.github.miguel.eventostec.model.Coupon;
import io.github.miguel.eventostec.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring", uses = CouponMapper.class)
public interface EventMapper {



    @Mapping(target = "address", source = "address", qualifiedByName = "addressToDto")
    EventResponseDTO toDto(Event event);

    @Mapping(target = "address", source = "address", qualifiedByName = "addressToDto")
    @Mapping(target = "coupons", source = "coupons")
    EventDetailsDTO toDetailsDto(Event event); // Nome alterado para evitar conflito

    @Named("addressToDto")
    static AddressResponseDTO addressToDto(Address address){
        if (address == null) {
            return null;
        }
        return new AddressResponseDTO(address.getUf(), address.getCity());
    }

}

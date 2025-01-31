package io.github.miguel.eventostec.mappers;

import io.github.miguel.eventostec.dto.AddressResponseDTO;
import io.github.miguel.eventostec.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    public AddressResponseDTO toDto(Address address);
}

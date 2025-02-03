package io.github.miguel.eventostec.repository;

import io.github.miguel.eventostec.model.Address;
import io.github.miguel.eventostec.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    Address findByEvent(Event event);
}

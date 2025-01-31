package io.github.miguel.eventostec.service;

import io.github.miguel.eventostec.model.Address;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;

    public void saveAddress(String uf, String city, Event event){
        Address newAddres = new Address(uf,city,event);
        repository.save(newAddres);
    }

    public Address findAddressByEvent(Event event){
        return repository.findByEvent(event);
    }
}

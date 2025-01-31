package io.github.miguel.eventostec.service;

import com.amazonaws.services.s3.AmazonS3;
import io.github.miguel.eventostec.dto.EventDetailsDTO;
import io.github.miguel.eventostec.dto.EventRequestDTO;
import io.github.miguel.eventostec.dto.EventResponseDTO;
import io.github.miguel.eventostec.mappers.AddressMapper;
import io.github.miguel.eventostec.mappers.EventMapper;
import io.github.miguel.eventostec.model.Event;
import io.github.miguel.eventostec.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService{

    @Value("${aws.bucket.name}")
    private String bucketName;


    private final AmazonS3 s3Client;
    private final EventRepository repository;
    private final AddressService addressService;
    private final EventMapper eventMapper;
    private final AddressMapper addressMapper;

    public Event createEvent(EventRequestDTO dto){
        String imgUrl = null;

        if(!dto.image().isEmpty()){
            imgUrl = this.uploadImg(dto.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(dto.title());
        newEvent.setRemote(dto.remote());
        newEvent.setEventUrl(dto.eventUrl());
        newEvent.setImgUrl(imgUrl);
        newEvent.setDescription(dto.description());
        newEvent.setDate(new Date(dto.date()));
        repository.save(newEvent);

        if(dto.remote().equals(false)){
            addressService.saveAddress(dto.state(),dto.city(),newEvent);
        }

        return newEvent;

    }

    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try{
            File file = this.convertMultipaartToFile(multipartFile);
            s3Client.putObject(bucketName, fileName, file);
            file.delete();
            return s3Client.getUrl(bucketName,fileName).toString();
        }catch (Exception e){
            System.out.println("Erro ao subir arquivo");
            return "";
        }

    }

    private File convertMultipaartToFile(MultipartFile multipartFile) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convertedFile;
    }

    public Page<EventResponseDTO> getAllEvents(Integer page, Integer size, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.valueOf(direction),orderBy);

        //procura eventos que ainda não aconteceram, e mapeia Event para EventResponseDTO
        return repository.findUpcomingEvents(new Date(),pageRequest)
                .map(eventMapper::toDto);
    }

    public Event finEventById(UUID id){
        return repository.findById(id).orElse(null);
    }

    public Page<EventResponseDTO> getFilteredEvents(Integer page, Integer size, String orderBy, String direction,
                                                    String title, String city, String state, Date startDate, Date endDate) {
        if(startDate == null){
            startDate = new Date();
        }
        if(endDate == null){
            endDate = Date.from(LocalDate.of(2999, 12, 31)
                    .atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.valueOf(direction),orderBy);
        return repository.findFilteredEvents(title, city, state, startDate, endDate,pageRequest).map(eventMapper::toDto);

    }

    public EventDetailsDTO getEventDetails(UUID eventId){
        return repository.findById(eventId).map(eventMapper::toDetailsDto).orElse(null);
    }
}

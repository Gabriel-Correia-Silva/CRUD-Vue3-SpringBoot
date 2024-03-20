package com.npi.springboot.controllers;

import com.npi.springboot.dtos.EventRecordDtos;
import com.npi.springboot.models.EventModel;
import com.npi.springboot.repositories.EventRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository; // Repositório para acesso aos dados dos eventos

    // Método para criar um novo evento
    @PostMapping("/event")
    public ResponseEntity<EventModel> createEvent(@RequestBody @Valid EventRecordDtos eventRecordDtos) {
        var eventModel = new EventModel();
        BeanUtils.copyProperties(eventRecordDtos, eventModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventRepository.save(eventModel));
    }

    // Método para obter um evento pelo nome
    @GetMapping("/event/{name}")
    public ResponseEntity<Object> getEventByName(@PathVariable String name) {
        Optional<EventModel> event = Optional.ofNullable(eventRepository.findByName(name));
        return event.<ResponseEntity<Object>>map(eventModel -> ResponseEntity.status(HttpStatus.OK).body(eventModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Evento não encontrado")));
    }

    // Método para obter todos os eventos
    @GetMapping("/event")
    public ResponseEntity<List<EventModel>> getAllEvents() {
        List<EventModel> events = eventRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(events);
    }

    // Método para atualizar um evento
    @PutMapping("/event/{id}")
    public ResponseEntity<EventModel> updateEvent(@PathVariable UUID id, @RequestBody @Valid EventRecordDtos eventRecordDtos) {
        try {
            Optional<EventModel> optionalEvent = eventRepository.findById(id);
            if (optionalEvent.isPresent()) {
                EventModel eventModel = optionalEvent.get();
                BeanUtils.copyProperties(eventRecordDtos, eventModel);
                eventModel.setId(id);
                return ResponseEntity.status(HttpStatus.OK).body(eventRepository.save(eventModel));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            // handle exception
        }
        return null;
    }



    // Método para excluir um evento
    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable UUID id) {
        Optional<EventModel> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Evento não encontrado"));
        }
    }
}
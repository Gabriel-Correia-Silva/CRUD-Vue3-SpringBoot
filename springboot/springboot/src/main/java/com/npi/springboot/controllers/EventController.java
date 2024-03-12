package com.npi.springboot.controllers;

import com.npi.springboot.dtos.EventRecordDtos;
import com.npi.springboot.models.EventModel;
import com.npi.springboot.repositories.EventRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @PostMapping("/event")
    public ResponseEntity<EventModel> createEvent(@RequestBody @Valid EventRecordDtos eventRecordDtos) {
        var eventModel = new EventModel();
        BeanUtils.copyProperties(eventRecordDtos, eventModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventRepository.save(eventModel));
    }

    @GetMapping("/event")
    public ResponseEntity<List<EventModel>> getAllEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(eventRepository.findAll());
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Object> getEventId(@PathVariable UUID id) {
        Optional<EventModel> event0 = eventRepository.findById(id);
        return event0.<ResponseEntity<Object>>map(eventModel -> ResponseEntity.status(HttpStatus.OK).body(eventModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Event not found")));
    }

    @PutMapping("/event/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable UUID id, @RequestBody @Valid EventRecordDtos eventRecordDtos) {
        Optional<EventModel> event0 = eventRepository.findById(id);
        return event0.<ResponseEntity<Object>>map(eventModel -> {
            BeanUtils.copyProperties(eventRecordDtos, eventModel);
            return ResponseEntity.status(HttpStatus.OK).body(eventRepository.save(eventModel));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Event not found")));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable UUID id) {
        Optional<EventModel> event0 = eventRepository.findById(id);
        return event0.<ResponseEntity<Object>>map(eventModel -> {
            eventRepository.delete(eventModel);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Event not found")));
    }


}
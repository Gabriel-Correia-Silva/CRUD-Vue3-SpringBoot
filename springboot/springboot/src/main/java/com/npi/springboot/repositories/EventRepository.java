package com.npi.springboot.repositories;

import com.npi.springboot.models.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventModel, UUID> {

    EventModel findByName(String name);

}
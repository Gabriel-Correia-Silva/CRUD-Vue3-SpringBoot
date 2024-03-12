package com.npi.springboot.repositories;

import com.npi.springboot.models.EventModel;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface EventRepository extends JpaRepository<EventModel, UUID> {

    Optional<EventModel> findById(SingularAttribute<AbstractPersistable, Serializable> id);
}

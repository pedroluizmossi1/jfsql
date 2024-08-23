package com.pedro.jfsql.repository;

import com.pedro.jfsql.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
    Optional<Endpoint> findByEndpoint(String endpoint);
}
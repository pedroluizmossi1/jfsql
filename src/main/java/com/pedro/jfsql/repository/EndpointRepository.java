package com.pedro.jfsql.repository;

import com.pedro.jfsql.model.Endpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepository extends JpaRepository<Endpoint, Long> {
}
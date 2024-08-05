package com.pedro.jfsql.repository;

import com.pedro.jfsql.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

}
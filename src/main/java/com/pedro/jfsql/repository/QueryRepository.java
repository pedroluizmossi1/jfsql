package com.pedro.jfsql.repository;

import com.pedro.jfsql.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryRepository extends JpaRepository<Query, Long> {
}
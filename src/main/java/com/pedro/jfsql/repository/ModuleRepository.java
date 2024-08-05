package com.pedro.jfsql.repository;

import com.pedro.jfsql.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    Optional<Module> findByName(String name);

    void deleteByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Module m SET m.active = :status WHERE m.name = :name")
    void setActiveByName(@Param("name") String name, @Param("status") boolean status);
}
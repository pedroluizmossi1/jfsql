package com.pedro.jfsql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "endpoints")
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "endpoint", nullable = false)
    private String endpoint;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private String created_at;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "updated_at", nullable = false)
    private String updated_at;


    @ManyToOne
    @JoinColumn(name = "connections_id")
    private Connection connection;

    @ManyToOne
    @JoinColumn(name = "querys_id")
    private Query query;

    public Endpoint() {
    }

}
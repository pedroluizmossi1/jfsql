package com.pedro.jfsql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pedro.jfsql.model.enumeration.DatabaseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "connections")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(value = {"endpoints"}, allowSetters = true)
public class Connection{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "connections_seq")
    @SequenceGenerator(name = "connections_seq", sequenceName = "connections_id_seq", allocationSize = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "port", nullable = false)
    private String port;

    @Column(name = "database", nullable = false)
    private String database;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "db_type", nullable = false)
    private DatabaseType databaseType;

    @Enumerated(EnumType.STRING)
    @Column(name = "db_conn_mode")
    private DatabaseConnMode databaseConnMode;

    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private String created_at;

    @Column(name = "updated_at", nullable = false)
    @JsonIgnore
    @UpdateTimestamp
    private String updated_at;

    @OneToMany(mappedBy = "connection")
    private List<Endpoint> endpoints;


    public Connection() {
    }

}
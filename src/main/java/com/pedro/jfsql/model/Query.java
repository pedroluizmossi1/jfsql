package com.pedro.jfsql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "queries")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(value = {"endpoints"}, allowSetters = true)
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queries_seq")
    @SequenceGenerator(name = "queries_seq", sequenceName = "queries_id_seq", allocationSize = 1)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "query", nullable = false)
    private String query;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "parameters", columnDefinition = "jsonb")
    @Type(JsonBinaryType.class)
    private String parameters;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    @CreationTimestamp
    private String created_at;

    @Column(name = "updated_at", nullable = false)
    @JsonIgnore
    @UpdateTimestamp
    private String updated_at;

    @OneToMany(mappedBy = "query")
    private List<Endpoint> endpoints;


    public Query() {
    }
}